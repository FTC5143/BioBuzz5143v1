package org.firstinspires.ftc.teamcode.xcentrics.components.live;

import com.pedropathing.controllers.Controller;
import com.pedropathing.controllers.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.xcentrics.components.Component;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;
import org.firstinspires.ftc.teamcode.xcentrics.util.qus.DcMotorQUS;
class WallConfig{
    public static double kP = 0,kI = 0,kD = 0;
    public static int target = 0;
}
public class Wall extends Component {
    private DcMotorQUS wall;
    private Controller pid = Controller.pid(WallConfig.kP,WallConfig.kI,WallConfig.kD);
    private double error = 0;
    public Wall(Robot robot) {
        super(robot);
    }

    public void registerHardware(HardwareMap hardwareMap){
        wall = new DcMotorQUS(hardwareMap.get(DcMotorEx.class,"Wall"));

        super.registerHardware(hardwareMap);
    }

    public void startup(){

        super.startup();
    }

    public void update(){
        super.update(opMode);
        wall.queue_power(pid.calculate(WallConfig.target,wall.motor.getCurrentPosition()-WallConfig.target));
        wall.update();
    }

    public void updateTelemetry(){
        super.updateTelemetry(telemetry);
    }
}
