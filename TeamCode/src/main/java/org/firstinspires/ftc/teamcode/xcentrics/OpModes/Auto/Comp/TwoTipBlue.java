package org.firstinspires.ftc.teamcode.xcentrics.OpModes.Auto.Comp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.xcentrics.OpModes.Auto.LiveAutoBase;
import org.firstinspires.ftc.teamcode.xcentrics.paths.BlueAutoPaths;
import org.firstinspires.ftc.teamcode.xcentrics.paths.Paths1;

@Autonomous(name = "Two Tip Auto Blue", group = "Auto")
public class TwoTipBlue extends LiveAutoBase {
    private static int autoState = 0;
    private BlueAutoPaths paths = new BlueAutoPaths();

    // Initialize FtcDashboard instance


    @Override
    public void on_init() {
        robot.follower.setPose(paths.start);
        autoState = 0;
    }

    @Override
    public void on_start() {

    }

    @Override
    public void on_stop() {

    }

    @Override
    public void on_loop() {

        switch (autoState){
            case 0:
                robot.shooter.spinUp();
                robot.intake.intake();
                robot.halt(5);
                robot.shooter.spinDown();
                autoState++;
                break;

            case 1:
                if(!robot.follower.isBusy()) {
                    robot.shooter.spinDown();
                    robot.intake.intake();
                    followPath(paths.path1());
                    robot.shooter.spinDown();
                    autoState++;
                    break;
                }

            case 2:
                if(!robot.follower.isBusy()){
                    robot.halt(1.5);
                    followPath(paths.path2());
                    autoState++;
                    break;
                }
            case 3:
                if(!robot.follower.isBusy()){
                    robot.shooter.spinUp();
                    robot.halt(4);
                    robot.shooter.spinDown();
                    autoState++;
                    break;
                }

            case 4:
                if(!robot.follower.isBusy()) {
                    followPath(paths.flowerready());
                    autoState++;
                    break;
                }

            case 5:
                if(!robot.follower.isBusy()){
                    robot.intake.lowerFlower();
                    robot.intake.intake();
                    robot.halt(1);
                    followPath(paths.path4());
                    robot.halt(3);
                    robot.intake.raiseFlower();
                    autoState++;
                    robot.intake.raiseFlower();
                    break;
                }

            case 6:
                if(!robot.follower.isBusy()){
                    robot.intake.raiseFlower();
                    followPath(paths.path5());
                    robot.intake.raiseFlower();
                    autoState++;
                    robot.intake.raiseFlower();
                    break;
                }

            case 7:
                if(!robot.follower.isBusy()){
                    robot.shooter.spinUp();
                    robot.halt(4);
                    robot.shooter.spinDown();
                    autoState++;
                    break;
                }

            case 8:
                if(!robot.follower.isBusy()){
                    followPath(paths.path6());
                    autoState++;
                    break;
                }

            case 9:
                break;

        }

        robot.addData("Auto State: ", autoState);
        robot.addData("Robot Pose: ", robot.getRobotPose());
        robot.addData("Follower Headding: ", Math.toDegrees(robot.follower.pose().heading()));
        robot.addData("Follower Pose: ", robot.follower.pose());
        robot.addData("path:", robot.follower.currentPath() != null ? robot.follower.currentPath() : "null");
        robot.updateTelemetry();
        if(autoState != 4 && autoState != 5){
            robot.intake.raiseFlower();
        }

    }
}
