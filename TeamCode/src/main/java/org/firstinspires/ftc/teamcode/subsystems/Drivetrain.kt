package org.firstinspires.ftc.teamcode.subsystems

import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.IMU
import dev.nextftc.hardware.actuators.NextMotor
import dev.nextftc.hardware.sensors.NextIMU
import dev.nextftc.robot.Mechanism
import java.util.function.Supplier
import kotlin.math.*


object Drivetrain : Mechanism {
    val frontLeft: NextMotor = NextMotor("LF")
    val frontRight: NextMotor = NextMotor("RF")
    val backLeft: NextMotor = NextMotor("LB")
    val backRight: NextMotor = NextMotor("RB")

    val imu = NextIMU("imu")


    init {
        frontLeft.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
        frontRight.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
        backLeft.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE
        backRight.zeroPowerBehavior = NextMotor.ZeroPowerBehavior.BRAKE

        frontLeft.direction = NextMotor.Direction.REVERSE
        backLeft.direction = NextMotor.Direction.REVERSE
        imu.initialize(IMU.Parameters(RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.LEFT, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD)))
    }

    fun mecanumDriveFieldCentric(
        frontLeft: NextMotor,
        frontRight: NextMotor,
        backLeft: NextMotor,
        backRight: NextMotor,
        imu: NextIMU,
        gamepad: Gamepad
    ): Command = Commands.infinite {

        val y = -gamepad.left_stick_y // Remember, Y stick value is reversed
        val x = gamepad.left_stick_x
        val rx = gamepad.right_stick_x
        val botHeading = imu.yaw
        // Rotate the movement direction counter to the bot's rotation
        var rotX: Double = x * cos(-botHeading) - y * sin(-botHeading)
        val rotY: Double = x * sin(-botHeading) + y * cos(-botHeading)

        rotX *= 1.1 // Counteract imperfect strafing


        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        val denominator: Double = (abs(rotY) + abs(rotX) + abs(rx)).coerceAtLeast(1.0)
        val frontLeftPower: Double = (rotY + rotX + rx) / denominator
        val backLeftPower: Double = (rotY - rotX + rx) / denominator
        val frontRightPower: Double = (rotY - rotX - rx) / denominator
        val backRightPower: Double = (rotY + rotX - rx) / denominator

        frontLeft.throttle = (frontLeftPower)
        backLeft.throttle = (backLeftPower)
        frontRight.throttle = (frontRightPower)
        backRight.throttle = (backRightPower)
    }

}