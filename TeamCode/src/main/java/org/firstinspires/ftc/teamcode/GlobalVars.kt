package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.math.Pose

@Configurable
object GlobalVars {

    @JvmField
    var RED_ALLIANCE = false

    @JvmField
    var FINAL_AUTO_POS = Pose(72.0, 72.0)

    @JvmField
    var SHOOTING = false

    @JvmField
    var SHOOTER_AIM = AutoAimCalcs.AimSolution()

    @JvmField
    var AUTO_AIM = false
}