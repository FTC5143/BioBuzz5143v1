package org.firstinspires.ftc.teamcode.xcentrics.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Paths1 {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    public final Pose start = poseFactory.of(55.4978, 9.0044, 90);
    private final Pose path1 = poseFactory.of(55.4955, 17.0411, -90);
    private final Pose point2Start = poseFactory.of(55.4955, 17.0411, -90);
    private final Pose point2 = poseFactory.of(55.4902, 17.0913, 90);
    private final Pose point3 = poseFactory.of(58.9408, 133.2604, 90);
    private final Pose point3Control1 = poseFactory.of(61.9986, 90.6798, 0);
    private final Pose point3Control2 = poseFactory.of(55.4862, 64.5471, 0);
    private final Pose point4 = poseFactory.of(46.9451, 129.0692, 90);
    private final Pose point4Control1 = poseFactory.of(60.9553, 129.6106, 0);
    private final Pose point4Control2 = poseFactory.of(57.8444, 113.167, 0);
    private final Pose point5 = poseFactory.of(58.872, 131.4909, 90);
    private final Pose point5Control1 = poseFactory.of(48.0322, 111.1761, 0);
    private final Pose point5Control2 = poseFactory.of(64.8935, 117.9366, 0);
    private final Pose point6 = poseFactory.of(8.7062, 121.8828, 90);
    private final Pose point6Control1 = poseFactory.of(68.8171, 128.8419, 0);
    private final Pose point6Control2 = poseFactory.of(37.0525, 119.6874, 0);

    public Path path1() {
        return Paths.line(start, path1).constant(path1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point2, point3Control1, point3Control2, point3).constant(point3);
    }

    public Path path4() {
        return Paths.curve(point3, point4Control1, point4Control2, point4).constant(point4);
    }

    public Path path5() {
        return Paths.curve(point4, point5Control1, point5Control2, point5).constant(point5);
    }

    public Path path6() {
        return Paths.curve(point5, point6Control1, point6Control2, point6).constant(point6);
    }
}