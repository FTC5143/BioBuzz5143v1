package org.firstinspires.ftc.teamcode.xcentrics.paths;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class HalfTipPaths {
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(60.7208, 133.2256, 90);
    private final Pose flowerReady = poseFactory.of(45.813, 122.5896, 90);
    private final Pose flowerReadyControl1 = poseFactory.of(53.1235, 111.627, 0);
    private final Pose flowerPower = poseFactory.of(46.8354, 127.9587, 90);
    private final Pose scoreFlowerPOWER = poseFactory.of(59.5878, 133.2618, 90);
    private final Pose scoreFlowerPOWERControl1 = poseFactory.of(52.853, 104.825, 0);
    private final Pose point4Start = poseFactory.of(59.5878, 133.2618, 90);
    private final Pose point4 = poseFactory.of(33.7895, 124.9092, 0);
    private final Pose point4Control1 = poseFactory.of(62.5971, 122.8177, 0);
    private final Pose point5 = poseFactory.of(9.2402, 116.237, 0);
    private final Pose point5Control1 = poseFactory.of(7.2369, 137.8224, 0);

    public Path flowerReady() {
        return Paths.curve(start, flowerReadyControl1, flowerReady).constant(flowerReady);
    }

    public Path flowerPower() {
        return Paths.line(flowerReady, flowerPower).constant(flowerPower);
    }

    public Path scoreFlowerPOWER() {
        return Paths.curve(flowerPower, scoreFlowerPOWERControl1, scoreFlowerPOWER).constant(scoreFlowerPOWER);
    }

    public Path path4() {
        return Paths.path(Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4), Paths.curve(point4, point5Control1, point5).constant(point5));
    }
    public Pose startPose(){
        return start;
    }
}
