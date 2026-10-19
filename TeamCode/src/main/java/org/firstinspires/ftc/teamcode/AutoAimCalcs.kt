package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.math.Pose
import com.qualcomm.robotcore.hardware.IMU
import kotlin.math.atan2
import kotlin.math.hypot
import dev.nextftc.core.units.deg
import org.firstinspires.ftc.teamcode.subsystems.Turret
import kotlin.math.roundToInt

@Configurable
object AutoAimCalcs {

    @JvmField var IdleRPM = 2400

    enum class HiveTip {
        LEFT_UP,
        RIGHT_UP
    }

    @JvmField
    var hiveTip = HiveTip.RIGHT_UP

    @JvmField
    var shooterEquation = Pair(0.0,0.0) // slope, intercept (y = ax+b)


    val redCellRight = Pose(0.0,0.0)
    val redCellLeft = Pose(0.0,0.0)

    val blueCellRight = Pose(0.0,0.0)
    val blueCellLeft = Pose(0.0,0.0)



    data class AimSolution(
        val turretAngleDeg: Double = 0.0,
        val distanceInches: Double = 0.0
    )

    data class ShotPoint(
        val distance: Double = 0.0,
        val shotRPM: Int = 0
    )

    val dataPoints = mutableListOf<ShotPoint>(
            ShotPoint()
        )

    fun calculateAim(robotPose: Pose): AimSolution {
        val target = when (GlobalVars.RED_ALLIANCE) {
            true -> when (hiveTip) {
                HiveTip.LEFT_UP -> redCellLeft
                HiveTip.RIGHT_UP -> redCellRight
            }

            false -> when (hiveTip) {
                HiveTip.LEFT_UP -> blueCellLeft
                HiveTip.RIGHT_UP -> blueCellRight
            }
        }

        val dx = target.x() - robotPose.x()
        val dy = target.y() - robotPose.y()

        val aimFieldCoords = Math.toDegrees(atan2(dy, dx))

        val aimRobotCoords = aimFieldCoords -
                Math.toDegrees(robotPose.heading())

        // Normalized -180 to 180
        val normalizedAngle =
            ((aimRobotCoords + 180.0) % 360.0 + 360.0) % 360.0 - 180.0

        return AimSolution(
            turretAngleDeg = normalizedAngle,
            distanceInches = hypot(dx, dy)
        )
    }

    fun calculateRPM(shooting: Boolean, distance: Double): Int {
        return if (shooting) {
            (shooterEquation.first * distance + shooterEquation.second).roundToInt() // shooterRPM = a*distance+b (y=ax+b)
        } else {
            IdleRPM
        }
    }
    fun generateShooterEquation() {
        val points = dataPoints

        require(points.size >= 2) {
            "At least two points are required"
        }

        val meanX = points.map { it.distance }.average()
        val meanY = points.map { it.shotRPM }.average()

        val numerator = points.sumOf {
            (it.distance - meanX) *
                    (it.shotRPM - meanY)
        }

        val denominator = points.sumOf {
            (it.distance - meanX) *
                    (it.distance - meanX)
        }

        require(denominator > 0.0) {
            "Points must have different distances"
        }

        val slope = numerator / denominator
        val intercept = meanY - slope * meanX

        shooterEquation = Pair(slope,intercept)
    }

    fun resetLocalization(robotPose: Pose): Pose {
        val xOffset = 8.18 // Half the length
        val yOffset = 7.21 // Half the width

        var returnPose = Pose(0.0,0.0, robotPose.heading())

        if (robotPose.x() > 72.0) {
            returnPose = returnPose.withX(144.0 - xOffset)
        } else {
            returnPose = returnPose.withX(xOffset)
        }

        if (robotPose.y() > 72.0) {
            returnPose = returnPose.withY(144.0 - yOffset)
        } else {
            returnPose = returnPose.withY(yOffset)
        }

        return returnPose
    }



}