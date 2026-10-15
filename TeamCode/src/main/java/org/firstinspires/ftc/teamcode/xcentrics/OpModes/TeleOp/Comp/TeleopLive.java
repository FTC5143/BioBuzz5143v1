package org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.Comp;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.LiveTeleopBase;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;


@TeleOp(name = "TeleOp")
public class TeleopLive extends LiveTeleopBase {

    @Override
    public void on_init() {
        robot.follower.setPose(robot.getRobotPose());
    }

    @Override
    public void on_start() {

    }

    @Override
    public void on_stop() {

    }

    @Override
    public void on_loop() {


        robot.follower.manual(
                0 - gamepad1.left_stick_y,
                0 - gamepad1.left_stick_x,
                0 - gamepad1.right_stick_x
        );


        if(gamepad2.y) {
            robot.shooter.spinUp();
        } else {
            robot.shooter.spinDown();
        }

        if(gamepad1.left_bumper){
            robot.intake.reverse();
        } else if (gamepad1.right_bumper) {
            robot.intake.intake();
        } else {
            robot.intake.stopIntake();
        }

    }
}
