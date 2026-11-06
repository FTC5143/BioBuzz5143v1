package org.firstinspires.ftc.teamcode.xcentrics.components.live;




import com.aaravdhawan25.pidautotuner.PIDFController;
import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.tuning.autotune.Tuner;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.xcentrics.components.Component;
import org.firstinspires.ftc.teamcode.xcentrics.robots.LiveRobot;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;
import org.firstinspires.ftc.teamcode.xcentrics.util.qus.CRServoQUS;
import org.firstinspires.ftc.teamcode.xcentrics.util.qus.DcMotorQUS;

@Configurable
class LauncherConfig{
    public static int TARGET_VELO = 1250;
   // public static PIDFCoefficients flyPidCoef = new PIDFCoefficients(40,0,0,12.5);
    public static int LAUNCHER_MIN_VELO = 1235;
    public static double p = 0.2451,i = 0.03308,d = 0.00034,f = 0;
}
@Configurable
public class Shooter extends Component {

    public DcMotorQUS fly;
    private PIDFController flyController = new PIDFController(0.2451,0.03308,0.00034,0);
    private CRServoQUS gate;
    private LiveRobot robot;
    public static double TARGET_SPEED = 0,gate1 = 0;
    public static double speed = 2700;
    public Shooter(LiveRobot robot) {
        super(robot);
        this.robot = robot;
    }

    @Override
    public void registerHardware(HardwareMap hwmap) {
        super.registerHardware(hwmap);
        fly = new DcMotorQUS(hwmap.get(DcMotorEx.class,"fly"));
        gate = new CRServoQUS(hwmap.get(CRServo.class,"gate"));
    }

    @Override
    public void startup() {
        super.startup();

        fly.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // fly.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //fly.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,LauncherConfig.flyPidCoef);


    }

    @Override
    public void update(LinearOpMode opmode) {
        super.update(opmode);
        if(Math.abs(toRPM(fly.motor.getVelocity()) - TARGET_SPEED) < 50 && TARGET_SPEED != 0){
            gate.queue_power(1);
        } else {
            gate.queue_power(0);
        }
        setShooterPID(TARGET_SPEED);
        gate.update();
        fly.update();
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        robot.addData("fly target:", LauncherConfig.TARGET_VELO);
        super.updateTelemetry(telemetry);
    }

    public void shutdown(){

    }

    private double toRPM(double tps){
        return tps * 60.0 / 28.0;
    }
    public void setShooterPID(double rpm){
        flyController = new PIDFController(LauncherConfig.p,LauncherConfig.i,LauncherConfig.d,LauncherConfig.f);
        double velocity = Math.abs(fly.motor.getVelocity());
        double currentRPM = toRPM(velocity);
        flyController.setOutputBounds(0.0,1);

        double power = flyController.calculate(currentRPM,rpm);
        if(rpm == 0){
            power = 0;
        }
        fly.queue_power(power);
    }

    public void spinUp(){
        TARGET_SPEED = speed;
    }
    public void spinDown(){
        TARGET_SPEED = 0;
    }

    public void out(){
        TARGET_SPEED = -100;
    }
    public void spinGate(){
        gate1 = 1;
    }

    public void stopGate(){
        gate1 = 0;
    }

}
