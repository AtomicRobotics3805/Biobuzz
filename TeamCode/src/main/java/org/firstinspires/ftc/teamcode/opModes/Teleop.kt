package org.firstinspires.ftc.teamcode.opModes

import com.bylazar.gamepad.PanelsGamepad
import com.pedropathing.follower.ManualDrive
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Gamepad
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.robot.drive.mecanumDriveFieldCentric
import dev.nextftc.robot.opmode.NextOpMode
import dev.nextftc.robot.opmode.NextTeleop
import org.firstinspires.ftc.teamcode.Allanite
import dev.nextftc.robot.triggers.CommandGamepad
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion
import org.firstinspires.ftc.teamcode.Allanite.follower
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.Logger
import org.firstinspires.ftc.teamcode.pedro.Constants
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.subsystems.Gate
import org.firstinspires.ftc.teamcode.subsystems.Gate.instant
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.MechanismRoutines

@NextTeleop("TeleOp")
class TeleOp(val robot: Allanite) : NextOpMode(robot) {

    val g1 = PanelsGamepad.firstManager
    val driver = CommandGamepad(gamepad1)
    val operator = CommandGamepad(gamepad2)


    override fun start() {
        Allanite.globalOnStart()
        buildDriverControls()
        Logger.setTelemetry(telemetry)
    }

    fun buildDriverControls() {
        Drivetrain.mecanumDriveFieldCentric(
            Drivetrain.frontLeft,
            Drivetrain.frontRight,
            Drivetrain.backLeft,
            Drivetrain.backRight,
            Drivetrain.imu,
            gamepad1
        ).schedule()



        driver.rightStickButton.toggleOnTrue(
            instant {
                Drivetrain.imu.resetYaw()
            }
        ) // Reset IMU

        driver.rightTrigger.isOver(0.5).toggleOnTrue( Intake.start() ).toggleOnFalse( Intake.slowIn() )

        driver.leftTrigger.isOver(0.5).toggleOnTrue( Intake.reverse() ).toggleOnFalse( Intake.slowIn() )

        driver.leftBumper.toggleOnTrue(Gate.open() ).toggleOnFalse( Gate.close() )

        driver.a.toggleOnTrue(Gate.fixProng()).toggleOnFalse(Gate.close())

    }

    override fun end() {
    }
}