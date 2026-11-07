package org.firstinspires.ftc.teamcode.xcentrics.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Paths1 {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(8.9915, 11.9666, -90);
    private final Pose path1Control1 = poseFactory.of(26.6988, 25.2602, 0);
    private final Pose point2Start = poseFactory.of(8.9915, 11.9666, -90);
    private final Pose point2 = poseFactory.of(59.6951, 131.5574, 90);
    private final Pose point2Control1 = poseFactory.of(33.1331, 83.1535, 0);
    private final Pose point2Control2 = poseFactory.of(30.3276, 96.1262, 0);
    private final Pose flowerready = poseFactory.of(46.6934, 118.1541, 90);
    private final Pose flowerreadyControl1 = poseFactory.of(52.8945, 104.9858, 0);
    private final Pose point4 = poseFactory.of(46.3031, 128.3569, 90);
    private final Pose point5 = poseFactory.of(59.84, 132.0084, 90);
    private final Pose point5Control1 = poseFactory.of(51.9807, 102.6102, 0);
    private final Pose point6Start = poseFactory.of(59.84, 132.0084, 90);
    private final Pose point6 = poseFactory.of(10.1755, 122.1578, 0);
    private final Pose point6Control1 = poseFactory.of(59.2587, 110.7255, 0);

    public Path path1() {
        return Paths.curve(start, path1Control1, path1).constant(path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2Control2, point2).linear(point2Start, point2);
    }

    public Path flowerready() {
        return Paths.curve(point2, flowerreadyControl1, flowerready).constant(flowerready);
    }

    public Path path4() {
        return Paths.line(flowerready, point4).constant(point4);
    }

    public Path path5() {
        return Paths.curve(point4, point5Control1, point5).constant(point5);
    }

    public Path path6() {
        return Paths.curve(point6Start, point6Control1, point6).linear(point6Start, point6);
    }
}