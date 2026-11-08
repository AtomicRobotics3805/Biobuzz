package org.firstinspires.ftc.teamcode.subsystems

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.util.RobotLog
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedback.PIDController
import dev.nextftc.control.feedforward.SimpleFFCoefficients
import dev.nextftc.control.feedforward.SimpleFeedforward
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.Mechanism
import dev.nextftc.units.RotationsPerMinute
import dev.nextftc.units.Unit
import dev.nextftc.units.gradians
import dev.nextftc.units.measuretypes.AngularVelocity
import dev.nextftc.units.radians
import dev.nextftc.units.rotations
import dev.nextftc.units.rpm
import dev.nextftc.units.unittypes.AngularVelocityUnit
import org.firstinspires.ftc.teamcode.AutoAimCalcs
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.GlobalVars.AUTO_DISTANCING
import org.firstinspires.ftc.teamcode.GlobalVars.SHOOTING
import org.firstinspires.ftc.teamcode.Logger
import kotlin.math.PI

@Configurable
object Shooter : Mechanism {

    var targetRPM = 2500.0 //TODO

    val motor = NextMotor("shooter")

    @JvmField var FFCoefficients = SimpleFFCoefficients(0.0005, 0.0, 0.055)
    @JvmField var PIDCoefficients = PIDCoefficients(0.006, 0.0, 0.018)

    var ff = SimpleFeedforward(FFCoefficients)
    var pid = PIDController(PIDCoefficients)
    init {
        motor.anglePerCount = (2.0 * PI / 28.0).radians
        motor.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.FLOAT
//        AutoAimCalcs.generateShooterEquation()
    }

    fun start() = instant {
            SHOOTING = true
        }

    fun stop() =  instant {
            SHOOTING = false
        }

    override fun periodic() {
        motor.throttle = (pid.calculate(error = targetRPM - motor.encoderVelocity.into(RotationsPerMinute)) + ff.calculate(targetRPM)).coerceIn(-1.0,1.0)
        if (!AUTO_DISTANCING) {
            AutoAimCalcs.calculateRPM(SHOOTING, GlobalVars.SHOOTER_AIM.distanceInches)
        }
        Logger.addData("Shooter power: ", motor.throttle)
        Logger.addData("Target vel: ", targetRPM)
        Logger.addData("Current vel", motor.encoderVelocity.magnitude)
        pid = PIDController(PIDCoefficients)
        ff = SimpleFeedforward(FFCoefficients)
    }

}
