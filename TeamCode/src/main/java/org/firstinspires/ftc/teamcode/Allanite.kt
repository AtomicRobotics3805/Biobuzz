package org.firstinspires.ftc.teamcode

import com.bylazar.field.PanelsField
import com.bylazar.opmodecontrol.ActiveOpMode
import com.bylazar.panels.Panels
import com.bylazar.panels.PanelsConfig
import com.pedropathing.follower.Follower
import dev.nextftc.hardware.RobotController
import dev.nextftc.hardware.RobotController.hardwareMap
import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.NextRobot
import dev.nextftc.units.Degrees
import dev.nextftc.units.unittypes.AngleUnit
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry
import org.firstinspires.ftc.teamcode.GlobalVars.OPMODE_START_TIME
import org.firstinspires.ftc.teamcode.pedro.Constants
import org.firstinspires.ftc.teamcode.subsystems.Gate
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Shooter
import org.firstinspires.ftc.teamcode.subsystems.Turret
import org.opencv.android.Utils

object Allanite : NextRobot {
    override val mechanisms by lazy { setOf(Gate, Intake, Shooter, Turret) }
    val panelsField = PanelsField.field

    init {
//        panelsField.setStyle("none", "white", 1.5)
//        panelsField.registerImage("TeamCode/src/main/res/raw/field_image.png")
//        panelsField.setOffsets(PanelsField.presets.PEDRO_PATHING)
    }


    private var _follower: Follower? = null

    val follower: Follower
        get() {
            if (_follower == null) {
                _follower = Constants.create(hardwareMap)
            }
            return _follower!!
        }

    fun globalOnStart() {
        OPMODE_START_TIME = System.currentTimeMillis()
    }

    override fun periodic() {
        follower.update()
        GlobalVars.SHOOTER_AIM = AutoAimCalcs.calculateAim(follower.pose())
//        Logger.log(
//            System.currentTimeMillis() - OPMODE_START_TIME,
//            follower.pose(),
//            Turret.targetPosition.into(Degrees),
//            Turret.turretPos.into(Degrees),
//            Shooter.targetRPM,
//            Shooter.motor.encoderVelocity.magnitude,
//            Shooter.motor.throttle
//        )
        Logger.update()

//        panelsField.cursorX = follower.pose().x()
//        panelsField.cursorY = follower.pose().y()
//        panelsField.cursorHeading = follower.pose().heading()
//        panelsField.rect( 14.42149,16.37425)
//        panelsField.update()
    }
}