package org.firstinspires.ftc.teamcode.xcentrics.components.live;




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
@Configurable
class LauncherConfig{
    public static int TARGET_VELO = 1300;
    public static PIDFCoefficients flyPidCoef = new PIDFCoefficients(40,0,0,12.5);
    public static int LAUNCHER_MIN_VELO = 1250;
}
public class Shooter extends Component {

    private DcMotorEx fly;
    private CRServoQUS gate;
    private LiveRobot robot;
    private int fly1 = 0,gate1 = 0;
    public Shooter(LiveRobot robot) {
        super(robot);
        this.robot = robot;
    }

    @Override
    public void registerHardware(HardwareMap hwmap) {
        super.registerHardware(hwmap);
        fly = (hwmap.get(DcMotorEx.class,"fly"));
        gate = new CRServoQUS(hwmap.get(CRServo.class,"gate"));
    }

    @Override
    public void startup() {
        super.startup();

        fly.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fly.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fly.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,LauncherConfig.flyPidCoef);


    }

    @Override
    public void update(LinearOpMode opmode) {
        super.update(opmode);
        if(fly.getVelocity() > LauncherConfig.LAUNCHER_MIN_VELO){
            gate.queue_power(1);
        } else {
            gate.queue_power(0);
        }
        gate.update();
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        super.updateTelemetry(telemetry);
    }

    public void shutdown(){

    }

    public void spinUp(){
        fly.setVelocity(LauncherConfig.TARGET_VELO);
        if(fly.getVelocity() > LauncherConfig.LAUNCHER_MIN_VELO){
            spinGate();
            robot.intake.intake();
        }
    }

    public void spinDown(){
        fly.setVelocity(0);
    }

    public void spinGate(){
        gate1 = 1;
    }

    public void stopGate(){
        gate1 = 0;
    }
}
