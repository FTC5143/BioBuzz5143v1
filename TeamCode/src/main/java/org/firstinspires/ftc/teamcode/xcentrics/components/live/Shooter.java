package org.firstinspires.ftc.teamcode.xcentrics.components.live;

import com.aaravdhawan25.pidautotuner.PIDFController;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.xcentrics.components.Component;
import org.firstinspires.ftc.teamcode.xcentrics.robots.LiveRobot;
import org.firstinspires.ftc.teamcode.xcentrics.util.qus.CRServoQUS;
import org.firstinspires.ftc.teamcode.xcentrics.util.qus.DcMotorQUS;

@Config
public class Shooter extends Component {

    public DcMotorQUS fly;
    private PIDFController flyController = new PIDFController(LauncherConfig.p, LauncherConfig.i, LauncherConfig.d, LauncherConfig.f);
    private CRServoQUS gate;
    private LiveRobot robot;

    // Separated onto individual lines for reliable FTC Dashboard mapping
    public static double TARGET_SPEED = 0;
    public static double gate1 = 0;
    public static double speed = 2700;

    private boolean isSpinningUp = false;

    public Shooter(LiveRobot robot) {
        super(robot);
        this.robot = robot;
    }

    @Override
    public void registerHardware(HardwareMap hwmap) {
        super.registerHardware(hwmap);
        fly = new DcMotorQUS(hwmap.get(DcMotorEx.class, "fly"));
        gate = new CRServoQUS(hwmap.get(CRServo.class, "gate"));
    }

    @Override
    public void startup() {
        super.startup();
        fly.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fly.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void update(LinearOpMode opmode) {
        super.update(opmode);
        if (isSpinningUp) {
            TARGET_SPEED = speed; // Overwrites TARGET_SPEED with your dashboard 'speed' setting
        }

        // FIXED: Added the missing '-' sign for the difference calculation
        if (Math.abs(toRPM(fly.motor.getVelocity()) - TARGET_SPEED) < 50 && TARGET_SPEED != 0) {
            gate.queue_power(1);
        } else {
            gate.queue_power(0);
        }

        if (TARGET_SPEED > -1) {
            setShooterPID(TARGET_SPEED);
        } else {
            fly.queue_power(-0.5);
        }
        gate.update();
        fly.update();
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        double currentRPM = toRPM(fly.motor.getVelocity());
        robot.addData("fly target RPM", TARGET_SPEED);
        robot.addData("fly current RPM", currentRPM);
        robot.addData("fly config speed", speed);
        robot.addData("fly PID p", LauncherConfig.p);
        robot.addData("fly PID i", LauncherConfig.i);
        robot.addData("fly PID d", LauncherConfig.d);
        robot.addData("fly PID f", LauncherConfig.f);
        super.updateTelemetry(telemetry);
    }

    public void shutdown() {

    }

    private double toRPM(double tps) {
        return tps * 60.0 / 28.0;
    }

    private double toTPS(double rpm) {
        return rpm * 28.0 / 60.0;
    }

    public void setShooterPID(double rpm) {
        // Correctly recreates the PID if Dashboard values are changed
        if (flyController.getKP() != LauncherConfig.p ||
                flyController.getKI() != LauncherConfig.i ||
                flyController.getKD() != LauncherConfig.d ||
                flyController.getKF() != LauncherConfig.f) {
            flyController = new PIDFController(LauncherConfig.p, LauncherConfig.i, LauncherConfig.d, LauncherConfig.f);
        }

        double velocity = fly.motor.getVelocity();
        double targetTPS = toTPS(rpm);
        flyController.setOutputBounds(0.0, 1.0);

        double power = flyController.calculate(velocity, targetTPS);
        if (rpm == 0) {
            power = 0;
        }
        fly.queue_power(power);
    }

    public void spinUp() {
        isSpinningUp = true;
        TARGET_SPEED = speed;
    }

    public void spinDown() {
        isSpinningUp = false;
        TARGET_SPEED = 0;
    }

    public void out() {
        isSpinningUp = false;
        TARGET_SPEED = -100;
    }

    public void spinGate() {
        gate1 = 1;
    }

    public void stopGate() {
        gate1 = 0;
    }
}