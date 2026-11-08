package org.firstinspires.ftc.teamcode.opModes

import dev.nextftc.robot.opmode.NextAutonomous
import dev.nextftc.robot.opmode.NextOpMode
import org.firstinspires.ftc.teamcode.Allanite
import org.firstinspires.ftc.teamcode.AutoRoutines
import org.firstinspires.ftc.teamcode.Logger

@NextAutonomous("Right Auto")
class RightAuto(val robot: Allanite) : NextOpMode(robot) {

    override fun start() {
        Allanite.globalOnStart()
        Logger.setTelemetry(telemetry)
        AutoRoutines.rightAuto().schedule()
    }

    override fun end() {
    }
}