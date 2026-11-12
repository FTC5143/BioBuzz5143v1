package org.firstinspires.ftc.teamcode.xcentrics.OpModes.Auto.Comp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.xcentrics.OpModes.Auto.LiveAutoBase;
import org.firstinspires.ftc.teamcode.xcentrics.paths.HalfTipPaths;
@Autonomous(name = "Half Tip Red", group = "Red")
public class HalfTipRed extends LiveAutoBase {
    private HalfTipPaths paths = new HalfTipPaths();
    private int autoState = 0;
    public static double wait = 5;
    @Override
    public void on_init() {
        robot.follower.setPose(paths.startPose());
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
                robot.halt(wait);
                robot.shooter.spinUp();
                robot.halt(5);
                robot.shooter.spinDown();
                followPath(paths.flowerReady());
                autoState++;
                break;

            case 1:
                if (!robot.follower.isBusy()){
                    robot.intake.lowerFlower();
                    robot.halt(1);
                    robot.intake.intake();
                    robot.halt(3);
                    followPath(paths.flowerPower());
                    autoState++;
                    break;
                }

            case 2:
                if(!robot.follower.isBusy()){
                    robot.intake.raiseFlower();
                    followPath(paths.scoreFlowerPOWER());
                    autoState++;
                    break;
                }

            case 3:
                if(!robot.follower.isBusy()){
                    robot.shooter.spinUp();
                    robot.halt(5);
                    robot.shooter.spinDown();
                    followPath(paths.path4());
                    autoState++;
                    break;
                }
        }

    }
}
