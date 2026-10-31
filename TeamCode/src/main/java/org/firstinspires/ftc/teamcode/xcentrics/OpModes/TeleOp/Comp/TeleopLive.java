package org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.Comp;


import com.pedropathing.controllers.PIDController;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.LiveTeleopBase;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;


@TeleOp(name = "TeleOp")
public class TeleopLive extends LiveTeleopBase {
    private DrivePowers drivePowers;

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
        drivePowers = new DrivePowers(
                0 - gamepad1.left_stick_y,
                0 - gamepad1.left_stick_x,
                0 - gamepad1.right_stick_x);


        if(gamepad1.left_bumper){
            if(robot.follower.pose().y()<= 70){
                ManualDrive.headingLock(
                        robot.follower,
                        new PIDController(5,0,0),
                        drivePowers,
                        Math.toRadians(270)
                );
            } else {
                ManualDrive.headingLock(
                        robot.follower,
                        new PIDController(5,0,0),
                        drivePowers,
                        Math.toRadians(90)
                );
            }
        } else{
            ManualDrive.driveOrHold(
                    robot.follower,
                    drivePowers
            );
        }

        if(gamepad2.y) {
            robot.shooter.spinUp();
        } else {
            robot.shooter.spinDown();
        }

        if(gamepad1.left_trigger < 0.5){
            robot.intake.reverse();
        } else if (gamepad1.right_trigger < 0.5) {
            robot.intake.intake();
        } else {
            robot.intake.stopIntake();
        }

    }
}
