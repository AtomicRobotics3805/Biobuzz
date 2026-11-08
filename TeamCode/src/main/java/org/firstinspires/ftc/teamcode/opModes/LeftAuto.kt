package org.firstinspires.ftc.teamcode.opModes

import com.bylazar.gamepad.PanelsGamepad
import com.pedropathing.follower.ManualDrive
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Gamepad
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.drive.mecanumDriveFieldCentric
import dev.nextftc.robot.opmode.NextAutonomous
import dev.nextftc.robot.opmode.NextOpMode
import dev.nextftc.robot.opmode.NextTeleop
import org.firstinspires.ftc.teamcode.Allanite
import dev.nextftc.robot.triggers.CommandGamepad
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion
import org.firstinspires.ftc.teamcode.Allanite.follower
import org.firstinspires.ftc.teamcode.AutoRoutines
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.Logger
import org.firstinspires.ftc.teamcode.pedro.Constants
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.subsystems.Gate
import org.firstinspires.ftc.teamcode.subsystems.Gate.instant
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.MechanismRoutines

@NextAutonomous("Left Auto")
class LeftAuto(val robot: Allanite) : NextOpMode(robot) {

    override fun start() {
        Allanite.globalOnStart()
        Logger.setTelemetry(telemetry)
        AutoRoutines.leftAuto().schedule()
    }

    override fun end() {
    }
}