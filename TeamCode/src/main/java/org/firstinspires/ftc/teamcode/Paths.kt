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

    val rightStart = factory.of(62.4, 7.2, 90.0)

    val rightEnd = factory.of(60.0, 70.75, 0.0)

    //endregion
    //region Path Definition

    fun leftPath(): Path = Paths.curve(leftStart, leftControl, leftPark).heading(Interpolator.piecewise()
        .until(0.0863, Interpolator.constant(leftStart))
        .until(1.0, Interpolator.linear(leftStart, leftPark)))

    fun rightPath(): Path = Paths.curve(rightStart, rightEnd).heading(Interpolator.piecewise()
        .until(0.8866, Interpolator.tangent)
        .until(1.0, Interpolator.linear(rightStart, rightEnd)))


    //endregion


    fun initialize() {
        if (GlobalVars.RED_ALLIANCE) {
            return
        } else {
            factory.mirrorX(0.0).mirrorY(0.0)
        }
    }

}