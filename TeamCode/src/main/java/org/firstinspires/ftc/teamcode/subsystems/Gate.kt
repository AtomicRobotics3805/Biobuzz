package org.firstinspires.ftc.teamcode.subsystems

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.util.RobotLog
import dev.nextftc.hardware.actuators.NextServo
import dev.nextftc.robot.Mechanism
import org.firstinspires.ftc.teamcode.Logger

@Configurable
object Gate : Mechanism {
    val servo = NextServo("gate")

    @JvmField
    var closePos = 0.4
    @JvmField
    var openPos = 0.1
    @JvmField
    var fixProngPos = 0.3


    fun open() = instant { servo.position = openPos }
    fun close() = instant { servo.position = closePos }

    fun fixProng() = instant { servo.position = fixProngPos }

    override fun periodic() {
        Logger.addData(
            "Gate Pos: ", if (servo.position == openPos) {
                "open"
            } else {
                "closed"
            }
        )
    }
}