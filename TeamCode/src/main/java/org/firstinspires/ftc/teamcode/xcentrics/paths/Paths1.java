package org.firstinspires.ftc.teamcode.xcentrics.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Paths1 {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 11.0365, -90);
    private final Pose path1 = poseFactory.of(58.0243, 18.186, 90);
    private final Pose point2 = poseFactory.of(59.3441, 129.9728, 90);
    private final Pose point3Start = poseFactory.of(59.3441, 129.9728, 90);
    private final Pose point3 = poseFactory.of(45.446, 125.7508, 70);
    private final Pose point3Control1 = poseFactory.of(66.9757, 119.7661, 0);
    private final Pose point4Start = poseFactory.of(45.446, 125.7508, 70);
    private final Pose point4 = poseFactory.of(61.0773, 132.6044, 90);
    private final Pose point4Control1 = poseFactory.of(60.0844, 116.2475, 0);
    private final Pose point5Start = poseFactory.of(61.0773, 132.6044, 0);
    private final Pose point5 = poseFactory.of(10.0193, 121.4013, 0);
    private final Pose point5Control1 = poseFactory.of(69.231, 119.0536, 0);
    public Paths1(){

    }
    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).constant(point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.curve(point5Start, point5Control1, point5).linear(point5Start, point5);
    }
}