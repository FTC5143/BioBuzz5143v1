package org.firstinspires.ftc.teamcode.pedroPathing;


import com.pedropathing.algorithm.Foresight;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.localizers.ThreeWheelIMULocalizer;
import com.pedropathing.revhub.localizers.ThreeWheelLocalizer;
import com.pedropathing.revhub.localizers.TwoWheelLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;


import org.firstinspires.ftc.teamcode.pedroPathing.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.MecanumTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.ThreeWheelIMUTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.ThreeWheelTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.TwoWheelTuner;


public class Tuning {
    // Tuners go here
    @Tuner
    public static Procedure mecanumTuner() {
        return new MecanumTuner();
    }
    @Tuner
    public static Procedure twoWheelTuner() {
        return new TwoWheelTuner();
    }
    @Tuner
    public static Procedure threeWheelIMUTuner() {
        return new ThreeWheelIMUTuner();
    }
    @Tuner
    public static Procedure threeWheelTuner() {
        return new ThreeWheelTuner();
    }
 //   @Tuner
//    public static Procedure foresightTuner() {
//       // return new ForesightTuner((hardwareMap) -> new ThreeWheelIMULocalizer(hardwareMap, Constants.localizerConfig), (hardwareMap) -> new Mecanum(hardwareMap, Constants.drivetrainConfig));
//    }
    @Tuner
    public static Procedure tests() {
        return new Tests(hardwareMap -> new Mecanum(hardwareMap, Constants.drivetrainConfig), (hardwareMap -> new ThreeWheelIMULocalizer(hardwareMap, Constants.localizerConfig)), () -> new Foresight(Constants.foresightConfig));

    }

}