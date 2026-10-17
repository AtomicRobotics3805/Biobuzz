package org.firstinspires.ftc.teamcode

import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.units.deg
import dev.nextftc.extensions.pedro.FollowPath
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.TurnBy
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.MechanismRoutines
import org.firstinspires.ftc.teamcode.subsystems.Shooter

object AutoRoutines {

    @JvmField
    val leftAutoDelay = 0.0

    val leftAuto = SequentialGroup(
        Delay(leftAutoDelay),
        MechanismRoutines.BeginShoot,
        Delay(5.0),
        MechanismRoutines.EndShoot,
        FollowPath(Paths.leftPath(), holdEnd = true)
    )

    val rightAuto = SequentialGroup(
        MechanismRoutines.BeginShoot,
        Delay(5.0),
        MechanismRoutines.EndShoot,
        FollowPath(Paths.rightPath(), holdEnd = true)
    )
}