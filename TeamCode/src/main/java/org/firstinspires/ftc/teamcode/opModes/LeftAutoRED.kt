package org.firstinspires.ftc.teamcode.opModes

import com.bylazar.telemetry.JoinedTelemetry
import com.bylazar.telemetry.PanelsTelemetry
import com.pedropathing.follower.Follower
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.AutoRoutines
import org.firstinspires.ftc.teamcode.GlobalVars
import org.firstinspires.ftc.teamcode.Paths
import org.firstinspires.ftc.teamcode.pedroPathing.Constants
import org.firstinspires.ftc.teamcode.subsystems.Gate
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Shooter
import org.firstinspires.ftc.teamcode.subsystems.Turret

@Autonomous(name = "Left Auto RED")
class LeftAutoRED : NextFTCOpMode() {

    init {
        addComponents(
            SubsystemComponent(Shooter, Gate, Turret, Intake),
            BulkReadComponent,
            PedroComponent(Constants::create)
        )
        telemetry = JoinedTelemetry(PanelsTelemetry.ftcTelemetry, telemetry)
        GlobalVars.RED_ALLIANCE = true
        Paths.initialize()
        PedroComponent.follower.setPose(Paths.leftStart)
    }

    override fun onStartButtonPressed() {
        AutoRoutines.leftAuto()
    }

    override fun onStop() {
        Shooter.stop
        GlobalVars.FINAL_AUTO_POS = PedroComponent.follower.pose()
    }
}