package org.firstinspires.ftc.teamcode.subsystems

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.RobotLog
import dev.nextftc.control.KineticState
import dev.nextftc.control.builder.controlSystem
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.WaitUntil
import dev.nextftc.core.commands.utility.LambdaCommand
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.AutoAimCalcs
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.GlobalVars.SHOOTING
import kotlin.math.roundToInt

@Configurable
object Shooter : Subsystem {

    var shooterRPM = 2400

    val ticksPerRev = 112/4

    val motor = MotorEx("shooter")


    @JvmField var basicFFCoefficients = BasicFeedforwardParameters(0.0005, 0.0, 0.055)
    @JvmField var velPidCoefficients = PIDCoefficients(0.006, 0.0, 0.018)

    override fun initialize() {
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        controller.goal = KineticState()
        AutoAimCalcs.generateShooterEquation()
    }

    @JvmField
    val controller = controlSystem {
        velPid(velPidCoefficients)
        basicFF(basicFFCoefficients)
    }

    var loopCount = 0
    val loopThreshold = 40

    val checkWithinToleranceForCorrectNumberOfLoops = WaitUntil {
        if (loopCount >= loopThreshold) {
            true
        } else {
            if (motor.velocity > (controller.goal.velocity-10) && motor.velocity < (controller.goal.velocity + 60)) {
                loopCount++
            } else {
                loopCount = 0
            }

            false
        }
    }

    val start: Command
        get() {
            return LambdaCommand().setIsDone {
                motor.velocity > controller.goal.velocity
            }.setStart {
                SHOOTING = true
            }.requires(this)
        }

    val stop: Command
        get() {
            return LambdaCommand().setIsDone { true }.setStart {
                SHOOTING = false
            }.requires(this)
        }

    override fun periodic() {
        motor.power = controller.calculate(motor.state)
        shooterRPM = AutoAimCalcs.calculateRPM(SHOOTING, GlobalVars.SHOOTER_AIM.distanceInches)
        controller.goal = KineticState(0.0, (shooterRPM / 60.0) * ticksPerRev)

        RobotLog.d("Shooter power: ", motor.power)
        RobotLog.d("Target vel: ", shooterRPM)
        RobotLog.d("Current vel", motor.velocity)
    }

}
