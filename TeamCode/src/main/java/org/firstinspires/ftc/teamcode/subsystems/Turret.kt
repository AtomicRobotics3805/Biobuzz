package org.firstinspires.ftc.teamcode.subsystems

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import com.qualcomm.robotcore.hardware.AnalogInput
import com.qualcomm.robotcore.hardware.DcMotor
import dev.nextftc.control.KineticState
import dev.nextftc.control.builder.controlSystem
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.units.Angle
import dev.nextftc.core.units.deg
import dev.nextftc.core.units.rev
import dev.nextftc.ftc.ActiveOpMode.hardwareMap
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.GlobalVars.AUTO_AIM


@Configurable
object Turret : Subsystem {

    val motorTicksPerRev = 383.6

    val encoderToTurretRatio = 30.0/100.0

    val motorToTurretRatio = 20.0/100.0

    @JvmField
    var motorPosition = 0.0
    @JvmField
    var encoderPositon = 0.0

    @JvmField
    var targetPosition = 0.0.deg

    @JvmField
    val absoluteEncoderOffset = 0.0

    @JvmField
    var turretPos = 0.0.deg


    val motor = MotorEx("turretMotor")
    lateinit var encoder: AnalogInput

    @JvmField var basicFFCoefficients = BasicFeedforwardParameters(0.0005, 0.0, 0.055)
    @JvmField var pidCoefficients = PIDCoefficients(0.006, 0.0, 0.018)

    @JvmField
    val controller = controlSystem {
        velPid(pidCoefficients)
        basicFF(basicFFCoefficients)
    }

    override fun initialize() {
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        encoder = hardwareMap.get(AnalogInput::class.java, "turretEncoder")

        controller.goal = KineticState()
    }

    override fun periodic() {
        encoderPositon = ((encoder.voltage -0.043)/3.1 * 360) + absoluteEncoderOffset
        motorPosition = motor.currentPosition
        turretPos = motorPosition.turretMotorToTurretAngle()

        if (AUTO_AIM) {
            targetPosition = GlobalVars.SHOOTER_AIM.turretAngleDeg.deg
        }

        controller.goal = KineticState(targetPosition.inDeg)

        motor.power = controller.calculate(KineticState(turretPos.inDeg))
    }

    fun zeroTurret() {
        val turretAngleFromMotor =
            -(motor.currentPosition / motorTicksPerRev) /
                    motorToTurretRatio * 360.0

        val turretAngleFromEncoder = encoderPositon * encoderToTurretRatio

        val repeatAngle = 360.0 * encoderToTurretRatio

        val correctedAngle = (0..3)
            .map { turretAngleFromEncoder + (it - 1) * repeatAngle }
            .filter { it in -180.0..180.0 }
            .minByOrNull { kotlin.math.abs(it - turretAngleFromMotor) }
            ?: return

        val correctedTicks =
            -(correctedAngle / 360.0) /
                    motorToTurretRatio * motorTicksPerRev

        motor.currentPosition = correctedTicks
    }

    fun Double.turretMotorToTurretAngle(): Angle {
        return ((this/motorTicksPerRev)*motorToTurretRatio).rev
    }


}