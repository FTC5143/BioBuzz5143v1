package org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.Comp;


import com.bylazar.field.FieldManager;
import com.bylazar.field.PanelsField;
import com.bylazar.field.Style;
import com.pedropathing.controllers.PIDController;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.LiveTeleopBase;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;


@TeleOp(name = "TeleOp")
public class TeleopLive extends LiveTeleopBase {
    private DrivePowers drivePowers;
    private FieldManager fieldManager  = PanelsField.INSTANCE.getField();
    // Simulated robot position state
    private double robotX = -60.0;
    private double robotY = -60.0;
    private double robotHeadingRad = 0.0;

    private double lastTime = 0.0;
    private double progressT = 0.0;
    private final double pathDurationSec = 8.0;
    @Override
    public void on_init() {
        robot.follower.setPose(robot.getRobotPose());
//        fieldManager.setOffsets(PanelsField.INSTANCE.getPresets().getPEDRO_PATHING());
//        fieldManager.setStyle("red","blue",2.0);
//        fieldManager.moveCursor(0.0,0.0);
//        fieldManager.img(10,10,fieldManager.registerImage( PanelsField.INSTANCE.getImages().getBIOBUZZ().getDARK()));
//        robot.follower.setPose(robot.getRobotPose());
    }

    @Override
    public void on_start() {
        lastTime = getRuntime();
    }

    @Override
    public void on_stop() {

    }

    @Override
    public void on_loop() {
//        // 1. Get the live coordinates from Pedro Pathing
//        // Replace the 0.0s below with your actual follower pose methods
//        double robotX = 0.0; // e.g., follower.poseUpdater.getPose().getX()
//        double robotY = 0.0; // e.g., follower.poseUpdater.getPose().getY()
//        double robotHeadingRad = 0.0; // e.g., follower.poseUpdater.getPose().getHeading()
//
//        // -------------------------------------------------------------
//        // 2. Draw Robot Body (Blue circle)
//        // -------------------------------------------------------------
//        fieldManager.setStyle(new Style("blue", "white", 2.0));
//        fieldManager.moveCursor(robotX, robotY);
//        fieldManager.circle(9.0); // 18-inch robot scaled (9-inch radius)
//
//        // -------------------------------------------------------------
//        // 3. Draw Robot Heading Direction (Red pointer line)
//        // -------------------------------------------------------------
//        double headingLength = 12.0;
//        double headingEndX = robotX + headingLength * Math.cos(robotHeadingRad);
//        double headingEndY = robotY + headingLength * Math.sin(robotHeadingRad);
//
//        fieldManager.setStyle(new Style("none", "red", 3.0));
//        fieldManager.moveCursor(robotX, robotY);
//        fieldManager.line(headingEndX, headingEndY);
//
//        // -------------------------------------------------------------
//        // 4. Send frame update to Panels dashboard
//        // -------------------------------------------------------------
//        fieldManager.update();
        drivePowers = new DrivePowers(
                0 - gamepad1.left_stick_y,
                0 - gamepad1.left_stick_x,
                0 - gamepad1.right_stick_x);


        if(gamepad1.left_bumper){
            DrivePowers lockedPowers;
            if(robot.follower.pose().y() <= 70){
                lockedPowers = ManualDrive.headingLock(
                        robot.follower,
                        new PIDController(0.5,0,0),
                        drivePowers,
                        Math.toRadians(270)
                );
            } else {
                lockedPowers = ManualDrive.headingLock(
                        robot.follower,
                        new PIDController(0.5,0,0),

                        drivePowers,
                        Math.toRadians(90)
                );
            }
            ManualDrive.driveOrHold(
                    robot.follower,
                    lockedPowers
            );
        } else {
            ManualDrive.driveOrHold(
                    robot.follower,
                    drivePowers
            );
        }

        if(gamepad1.y) {
            robot.shooter.spinUp();
            robot.intake.intake();
        }else if(gamepad1.dpad_left){
            robot.shooter.out();
        }
        else {
            robot.shooter.spinDown();
            robot.intake.stopIntake();
        }

        if(gamepad1.right_trigger > 0.1){
            robot.intake.intake();
        } else if (gamepad1.left_trigger > 0.1) {
            robot.intake.reverse();
        } else {
            robot.intake.stopIntake();
        }
        if(gamepad2.dpad_down || gamepad1.dpad_down){
            robot.intake.lowerFlower();
        } else if (gamepad2.dpad_up || gamepad1.dpad_up){
            robot.intake.raiseFlower();
        }


        robot.addData("Robot Pose: ",robot.getRobotPose());
        robot.addData("Follower Headding: ",Math.toDegrees(robot.follower.pose().heading()));
        robot.addData("Follower Pose: ",robot.follower.pose());
        robot.addData("path:",robot.follower.currentPath() != null ? robot.follower.currentPath() : "null");
        robot.updateTelemetry();
    }
}
