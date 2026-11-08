package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.util.RobotLog
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.Mechanism
import org.firstinspires.ftc.teamcode.Logger

object Intake : Mechanism {
    val motor = NextMotor("intake")

    init {
        motor.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
        motor.direction = NextMotor.Direction.REVERSE
    }

    override fun periodic() {
        Logger.addData("Intake power: ", motor.throttle)
    }

    fun start() = instant { motor.throttle = 1.0 }
    fun slowIn() = instant { motor.throttle = 0.4 }
    fun slowOut() = instant { motor.throttle = -0.2 }
    fun stop() = instant { motor.throttle = 0.0 }
    fun reverse() = instant { motor.throttle = -1.0 }
}