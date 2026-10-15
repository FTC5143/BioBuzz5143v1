package org.firstinspires.ftc.teamcode.pedroPathing;


import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.ThreeWheelIMULocalizer;
import com.pedropathing.revhub.localizers.ThreeWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static MecanumConfig driveConfig = new MecanumConfig(
            config -> {
                config.frontLeftName.set("FL");
                config.frontRightName.set("FR");
                config.backLeftName.set("BL");
                config.backRightName.set("BR");
                config.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                config.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            }
    );
}