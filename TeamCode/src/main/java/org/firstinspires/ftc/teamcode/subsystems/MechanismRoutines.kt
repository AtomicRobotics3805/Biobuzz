package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.robot.coroutine.command

object MechanismRoutines {

    fun BeginShoot() = command {
        await(Shooter.start())
        await(command { Shooter.motor.velocityPID.error < 100.0})
        await(Gate.open())
        wait(0.2)
        await(Intake.start())
    }

    fun EndShoot() = command {
        await(Intake.stop())
        await(Gate.close())
        await(Shooter.stop())
    }
}