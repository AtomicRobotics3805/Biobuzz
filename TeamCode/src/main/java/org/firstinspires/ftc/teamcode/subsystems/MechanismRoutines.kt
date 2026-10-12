package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import org.firstinspires.ftc.teamcode.GlobalVars.SHOOT_1_BALL_DELAY
import org.firstinspires.ftc.teamcode.GlobalVars.SHOOT_3_BALL_DELAY

object MechanismRoutines {

    val BeginShoot = SequentialGroup(
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