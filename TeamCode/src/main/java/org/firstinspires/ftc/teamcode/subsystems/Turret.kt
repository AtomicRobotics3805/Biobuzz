package org.firstinspires.ftc.teamcode.subsystems

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.AnalogInput
import com.qualcomm.robotcore.util.RobotLog
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedback.PIDController
import dev.nextftc.control.feedforward.SimpleFFCoefficients
import dev.nextftc.control.feedforward.SimpleFeedforward
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.Mechanism
import dev.nextftc.units.Degrees
import dev.nextftc.units.degrees
import dev.nextftc.units.measuretypes.Angle
import dev.nextftc.units.rotations
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.GlobalVars.AUTO_AIM
import org.firstinspires.ftc.teamcode.Logger

@Configurable
object Turret : Mechanism {

    val motorTicksPerRev = 383.6

    val motorToTurretRatio = 20.0/100.0

    @JvmField
    var motorPosition = 0.0.degrees

    @JvmField
    var targetPosition = 0.0.degrees
    @JvmField
    var turretPos = 0.0.degrees

    val motor = NextMotor("turret", (motorTicksPerRev / 360).degrees)
    lateinit var encoder: AnalogInput

    @JvmField var FFCoefficients = SimpleFFCoefficients(0.0005, 0.0, 0.055)
    @JvmField var PIDCoefficients = PIDCoefficients(0.006, 0.0, 0.018)


    init {
        motor.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
        motor.anglePerCount = (360/motorTicksPerRev).degrees
        motor.velocityConstants.apply {
            this.kP = this@Turret.PIDCoefficients.kP
            this.kI = this@Turret.PIDCoefficients.kI
            this.kD = this@Turret.PIDCoefficients.kD
            this.kS = this@Turret.FFCoefficients.kS
            this.kV = this@Turret.FFCoefficients.kV
            this.kA = this@Turret.FFCoefficients.kA
        }
    }

    override fun periodic() {
        motorPosition = motor.encoderPosition
        turretPos = motorPosition.turretMotorToTurretAngle()

        if (AUTO_AIM) {
            targetPosition = GlobalVars.SHOOTER_AIM.turretAngleDeg.degrees
        } else {
            targetPosition = 0.0.degrees
        }

        motor.setAbsolutePositionSetpoint(targetPosition)

        Logger.addData("Turret pos", turretPos)
    }


    fun Angle.turretMotorToTurretAngle(): Angle {
        return ((this.into(Degrees)/motorTicksPerRev)*motorToTurretRatio).degrees
    }


}