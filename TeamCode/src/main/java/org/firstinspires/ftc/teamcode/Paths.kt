package org.firstinspires.ftc.teamcode

import com.pedropathing.api.Paths
import com.pedropathing.api.PoseFactory
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import com.pedropathing.paths.interpolator.Interpolator

object Paths {

    val factory = PoseFactory.degrees()

    //region Poses

    val leftStart = factory.of(63.0,133.2, 270.0)
    val leftControl = factory.of(46.4, 110.6, 0.0)
    val leftPark = factory.of(11.0,119.0,0.0)

    val right1Start = factory.of(62.4, 7.2, 90.0)
    val right1Control1 = factory.of(52.2, 25.0, 0.0)
    val right1Control2 = factory.of(28.6, 7.21, -164.6808)
    val right1End = factory.of(60.0, 70.75, 0.0)

    val right2Control = factory.of(26.3, 48.5, 100.3301)
    val right2End = factory.of(11.0, 96.0, 0.0)

    //endregion
    //region Path Definition

    fun leftPath(): Path = Paths.curve(leftStart, leftControl, leftPark).heading(Interpolator.piecewise()
        .until(0.0863, Interpolator.constant(leftStart))
        .until(1.0, Interpolator.linear(leftStart, leftPark)))

    fun rightPath(): Path = Paths.path(rightPath1(), rightPath2())

    fun rightPath1(): Path = Paths.curve(right1Start, right1Control1, right1Control2, right1End).heading(Interpolator.piecewise()
        .until(0.035, Interpolator.constant(right1Start))
        .until(0.767, Interpolator.tangent)
        .until(0.864, Interpolator.linear(right1Control2, right1End))
        .until(1.0, Interpolator.constant(right1End)))

    fun rightPath2(): Path = Paths.curve(right1End, right2Control, right2End).heading(Interpolator.piecewise()
        .until(0.168, Interpolator.constant(right1End))
        .until(0.762, Interpolator.tangent)
        .until(1.0, Interpolator.linear(right2Control, right2End)))


    //endregion


    fun initialize() {
        if (GlobalVars.RED_ALLIANCE) {
            return
        } else {
            factory.mirrorX(0.0).mirrorY(0.0)
        }
    }

}