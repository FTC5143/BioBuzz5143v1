package org.firstinspires.ftc.teamcode.pedroPathing;


import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.RevHubIMU;
import com.pedropathing.revhub.localizers.ThreeWheelIMUConfig;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            config -> {
                config.frontLeftName.set("FL");
                config.frontRightName.set("FR");
                config.backLeftName.set("BL");
                config.backRightName.set("BR");
                config.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                config.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            }
    );
    public static ThreeWheelIMUConfig localizerConfig = new ThreeWheelIMUConfig(c -> {
        c.leftEncoderName.set("BL");
        c.rightEncoderName.set("FR");
        c.strafeEncoderName.set("BR");
        c.imuName.set("imu");
        c.imu.set(new RevHubIMU(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        )));
        c.leftPodY.set(7.095924688912749);
        c.rightPodY.set(-8.455540747150192);
        c.strafePodX.set(-6.774370967629784);
        c.forwardTicksToInches.set(0.0019575434556651557);
        c.strafeTicksToInches.set(0.001981550809255165);
        c.turnTicksToRadians.set(0.0019365162934119827);
        c.leftEncoderDirection.set(Encoder.FORWARD);
        c.rightEncoderDirection.set(Encoder.FORWARD);
        c.strafeEncoderDirection.set(Encoder.REVERSE);
    });
}