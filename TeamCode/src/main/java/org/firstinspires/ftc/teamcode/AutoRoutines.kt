package org.firstinspires.ftc.teamcode

import android.net.wifi.aware.AwareResources
import dev.nextftc.robot.Mechanism
import dev.nextftc.robot.coroutine.command
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.subsystems.Gate
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Shooter
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain.startAutoDrive
import org.firstinspires.ftc.teamcode.subsystems.MechanismRoutines

object AutoRoutines {

    val leftAutoStartDelay = 10.0

    fun rightAuto() = command {
        startAutoDrive(axialPower = -0.5)
        wait(0.5) //Todo
        startAutoDrive()
        await(MechanismRoutines.AutoShootVolley())
        //Shoot Preloads

        startAutoDrive(axialPower = -0.5)
        wait(0.2) //Todo
        startAutoDrive(lateralPower = 1.0)
        wait(1.0) //Todo
        startAutoDrive()
        //Drive to intake

        await(Intake.start())
        wait(1.0) //Todo (ensure balls are out of bot)
        startAutoDrive(axialPower = 0.5)
        wait(1.0) //Todo (run against the wall)
        startAutoDrive()
        wait(3.0)
        //Intake

        startAutoDrive(axialPower = -0.5)
        wait(0.2) //Todo
        startAutoDrive(lateralPower = -1.0)
        wait(1.0) //Todo (Stop at shoot pos)
        //Drive to shoot

        await(MechanismRoutines.AutoShootVolley())
        startAutoDrive(axialPower = -0.5)
        wait(0.5) //Todo (leave the wall)
        startAutoDrive()
        //Shoot and leave the wall
    }.requiring(Gate, Intake, Shooter, Drivetrain)

    fun leftAuto() = command {
        wait(leftAutoStartDelay)
        startAutoDrive(axialPower = -0.5)
        wait(0.5) //Todo
        startAutoDrive()
        await(MechanismRoutines.AutoShootVolley())
        //Shoot Preloads

        startAutoDrive(axialPower = -0.5, lateralPower = -1.0) //Todo (should park)
        wait(1.0) //Todo
    }.requiring(Gate, Intake, Shooter, Drivetrain)

}