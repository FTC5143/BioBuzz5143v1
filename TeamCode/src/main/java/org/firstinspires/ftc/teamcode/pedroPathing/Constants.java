package org.firstinspires.ftc.teamcode.pedroPathing;


import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.RevHubIMU;
import com.pedropathing.revhub.localizers.ThreeWheelIMUConfig;
import com.pedropathing.revhub.localizers.ThreeWheelIMULocalizer;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            config -> {
                config.frontLeftName.set("FL");
                config.frontRightName.set("FR");
                config.backLeftName.set("BL");
                config.backRightName.set("BR");
                config.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                config.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                config.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                config.backRightDirection.set(DcMotorSimple.Direction.FORWARD);

                config.manualBrakeMode.set(true);
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
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.2610484376206172);
                Controller secondaryTranslationalForward = Controller.proportional(0.0964504223580981);
                Controller primaryTranslationalLateral = Controller.proportional(0.31982413494612294);
                Controller secondaryTranslationalLateral = Controller.proportional(0.11816647200431536);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.013524391273037647));
                c.brake.set(Controller.proportionalFeedforward(0.011495732582081999));

                c.headingFeedback.set(Controller.proportional(5));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.06944465549537716, 0.002261174223295327));

                c.linearBrakeCoefficients.set(Matrix.diag(0.13306223305360412, 0.07234494402127903));
                c.quadraticBrakeCoefficients.set(Matrix.diag(-5.405862243939533E-4, 9.618181055288052E-4));

                c.maxAchievableForwardVelocity.set(74.34293959089521);
                c.maxAchievableStrafeVelocity.set(60.70535207374944);
                c.naturalForwardDeceleration.set(31.410933147205544);
                c.naturalStrafeDeceleration.set(60.54827610336701);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new ThreeWheelIMULocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}
