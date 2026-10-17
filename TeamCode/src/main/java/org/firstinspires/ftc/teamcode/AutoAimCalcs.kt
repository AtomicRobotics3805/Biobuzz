package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.math.Pose
import kotlin.math.atan2
import kotlin.math.hypot
import dev.nextftc.core.units.deg
import org.firstinspires.ftc.teamcode.subsystems.Turret

@Configurable
object AutoAimCalcs {

    @JvmField var IdleRPM = 2400

    enum class HiveTip {
        LEFT_UP,
        RIGHT_UP
    }

    @JvmField
    var hiveTip = HiveTip.RIGHT_UP


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

    val dataPoints = List(
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

    fun calculateRPM(shooting: Boolean, target: Double): Int {
        if (shooting) {

        } else {
            return IdleRPM
        }
    }

}