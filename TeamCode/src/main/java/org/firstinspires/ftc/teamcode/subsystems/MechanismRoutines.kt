package org.firstinspires.ftc.teamcode.subsystems

import dev.nextftc.robot.coroutine.command
import dev.nextftc.units.RotationsPerMinute

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

    fun AutoShootVolley() = command {
        await(Shooter.start())
        waitUntil { Shooter.motor.encoderVelocity.into(RotationsPerMinute) >= 250 /*TODO() For some reason the encoder reads at 250 ish when its at 2400. need  to tune the exact number*/ }
        await(Intake.start())
        wait(2.0) //TODO
        await(Intake.slowIn())
        await(Gate.open())
        wait(2.0)
        await(Gate.close())
    }.requiring(Shooter, Intake, Gate)
}