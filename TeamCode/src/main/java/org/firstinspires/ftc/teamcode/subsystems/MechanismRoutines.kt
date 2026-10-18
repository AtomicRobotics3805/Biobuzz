package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
object MechanismRoutines {

    val BeginShoot = SequentialGroup(
        Shooter.start,
        Gate.open,
        Delay(0.2),
        Intake.start
    )

    val EndShoot = SequentialGroup(
        Intake.stop,
        Gate.close,
        Shooter.stop
    )

}