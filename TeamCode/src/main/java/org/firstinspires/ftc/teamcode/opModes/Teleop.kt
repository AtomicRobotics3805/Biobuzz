package org.firstinspires.ftc.teamcode.opModes

import com.bylazar.configurables.annotations.Configurable
import com.bylazar.telemetry.JoinedTelemetry
import com.bylazar.telemetry.PanelsTelemetry
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.core.units.deg
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import dev.nextftc.hardware.driving.FieldCentric
import dev.nextftc.hardware.driving.MecanumDriverControlled
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.AutoAimCalcs
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.pedroPathing.Constants
import org.firstinspires.ftc.teamcode.subsystems.Gate
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.MechanismRoutines
import org.firstinspires.ftc.teamcode.subsystems.Shooter
import org.firstinspires.ftc.teamcode.subsystems.Turret

@Configurable
@TeleOp(name = "Teleop")
class Teleop : NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(Intake, Shooter, Gate, Turret),
            BulkReadComponent,
            PedroComponent(Constants::create),
            BindingsComponent,
            LoopTimeComponent()
        )
        telemetry = JoinedTelemetry(PanelsTelemetry.ftcTelemetry, telemetry)
    }


    override fun onInit() {
    }

    private val frontLeftMotor = MotorEx("LF").brakeMode()
    private val frontRightMotor = MotorEx("RF").brakeMode()
    private val backLeftMotor = MotorEx("LB").brakeMode()
    private val backRightMotor = MotorEx("RB").brakeMode()


    override fun onStartButtonPressed() {
        PedroComponent.follower.setPose(GlobalVars.FINAL_AUTO_POS)
        Gate.close()

        val driverControlled = MecanumDriverControlled(
            frontLeftMotor,
            frontRightMotor,
            backLeftMotor,
            backRightMotor,
            -Gamepads.gamepad1.leftStickY,
            Gamepads.gamepad1.leftStickX,
            Gamepads.gamepad1.rightStickX,
            FieldCentric { PedroComponent.follower.pose().heading().deg }
        )

        driverControlled()


        Gamepads.gamepad1.rightStickButton.whenBecomesTrue {
            if (GlobalVars.RED_ALLIANCE) {
                PedroComponent.follower.setPose(PedroComponent.follower.pose().withHeading(0.0))
            } else {
                PedroComponent.follower.setPose(PedroComponent.follower.pose().withHeading(180.0))
            }
        } // Reset IMU

        Gamepads.gamepad1.leftStickButton.whenBecomesTrue { PedroComponent.follower.setPose(AutoAimCalcs.resetLocalization(
            PedroComponent.follower.pose())) } // Reset Localization

        Gamepads.gamepad1.rightTrigger.asButton { it >= 0.5 }.whenBecomesTrue { Intake.start() }.whenBecomesFalse { Intake.slowIn() }

        Gamepads.gamepad1.leftTrigger.asButton { it >= 0.5 }.whenBecomesTrue { Intake.reverse() }.whenBecomesFalse { Intake.slowIn() }

        Gamepads.gamepad1.leftBumper.whenBecomesTrue { MechanismRoutines.BeginShoot() }.whenBecomesFalse { MechanismRoutines.EndShoot() }
    }

    override fun onUpdate() {
        GlobalVars.SHOOTER_AIM = AutoAimCalcs.calculateAim(PedroComponent.follower.pose())
    }






}