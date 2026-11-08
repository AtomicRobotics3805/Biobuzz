package org.firstinspires.ftc.teamcode.pedro;

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
import com.pedropathing.revhub.localizers.TwoWheelConfig;
import com.pedropathing.revhub.localizers.TwoWheelLocalizer;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("LF");
        c.frontRightName.set("RF");
        c.backLeftName.set("LB");
        c.backRightName.set("RB");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
    public static TwoWheelConfig localizerConfig = new TwoWheelConfig(c -> {
        c.xPodName.set("LF");
        c.yPodName.set("RF");
        c.imuName.set("imu");
        c.xPodOffset.set(-7.657510176645227);
        c.yPodOffset.set(4.349309081297117);
        c.forwardTicksToInches.set(0.002968434);
        c.strafeTicksToInches.set(0.002968434);
        c.xPodDirection.set(Encoder.REVERSE);
        c.yPodDirection.set(Encoder.FORWARD);
        c.imu.set(new RevHubIMU(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        )));
    });


    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.2174887015603177);
                Controller secondaryTranslationalForward = Controller.proportional(0.08035626382140151);
                Controller primaryTranslationalLateral = Controller.proportional(0.3061745318058434);
                Controller secondaryTranslationalLateral = Controller.proportional(0.1131233083680952);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.007628427098454228));
                c.brake.set(Controller.proportionalFeedforward(0.006484163033686094));

                c.headingFeedback.set(Controller.proportional(2.731551630486217));
                c.headingBrakeCoefficients.set( Vector2D.cartesian(0.01886983835713449, 0.0015315338767992123));

                c.linearBrakeCoefficients.set(Matrix.diag(0.1519904681714611, 0.056449674883015395));
                c.quadraticBrakeCoefficients.set(Matrix.diag(-3.9352411087701686E-5, 0.0011316792710658412));

                c.maxAchievableForwardVelocity.set(118.66941535709934);
                c.maxAchievableStrafeVelocity.set(98.97571125806985);
                c.naturalForwardDeceleration.set(68.74628496433319);
                c.naturalStrafeDeceleration.set(114.8261239696177);
            }
    );
    public static Follower create(HardwareMap h) {
        return new Follower(
                new TwoWheelLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}