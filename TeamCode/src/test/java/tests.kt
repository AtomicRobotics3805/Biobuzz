package org.firstinspires.ftc.teamcode

import com.pedropathing.math.Pose
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows

class AutoAimCalcsTest {

    //region AUTO AIM
    @Test
    fun `target directly ahead gives zero degrees`() {
        // Target is at (0, 0); robot is 10 inches to its left.
        val robot = Pose(-10.0, 0.0, 0.0)

        val result = AutoAimCalcs.calculateAim(robot)

        assertEquals(0.0, result.turretAngleDeg, 1e-9)
        assertEquals(10.0, result.distanceInches, 1e-9)
    }

    @Test
    fun `target to the left gives positive 90 degrees`() {
        val robot = Pose(0.0, -10.0, 0.0)

        val result = AutoAimCalcs.calculateAim(robot)

        assertEquals(90.0, result.turretAngleDeg, 1e-9)
        assertEquals(10.0, result.distanceInches, 1e-9)
    }

    @Test
    fun `robot heading is subtracted from field angle`() {
        val robot = Pose(-10.0, 0.0, Math.PI / 2.0)

        val result = AutoAimCalcs.calculateAim(robot)

        assertEquals(-90.0, result.turretAngleDeg, 1e-9)
    }
    //endregion

    //region AUTO DISTANCING

    @Test
    fun `not shooting returns idle RPM`() {
        val oldIdleRPM = AutoAimCalcs.IdleRPM

        try {
            AutoAimCalcs.IdleRPM = 2400

            assertEquals(
                2400,
                AutoAimCalcs.calculateRPM(false, 50.0)
            )
        } finally {
            AutoAimCalcs.IdleRPM = oldIdleRPM
        }
    }

    @Test
    fun `shooting uses linear equation`() {
        val oldEquation = AutoAimCalcs.shooterEquation

        try {
            AutoAimCalcs.shooterEquation = Pair(20.0, 1000.0)

            assertEquals(
                1600,
                AutoAimCalcs.calculateRPM(true, 30.0)
            )
        } finally {
            AutoAimCalcs.shooterEquation = oldEquation
        }
    }

    @Test
    fun `RPM is rounded to nearest integer`() {
        val oldEquation = AutoAimCalcs.shooterEquation

        try {
            AutoAimCalcs.shooterEquation = Pair(0.5, 1000.0)

            assertEquals(1002, AutoAimCalcs.calculateRPM(true, 3.0))
        } finally {
            AutoAimCalcs.shooterEquation = oldEquation
        }
    }
    //endregion

    //region GENERATE SHOOTER EQUATION

    private fun withDataPoints(
        vararg points: AutoAimCalcs.ShotPoint,
        test: () -> Unit
    ) {
        val originalPoints = AutoAimCalcs.dataPoints.toList()
        val originalEquation = AutoAimCalcs.shooterEquation

        try {
            AutoAimCalcs.dataPoints.clear()
            AutoAimCalcs.dataPoints.addAll(points)
            test()
        } finally {
            AutoAimCalcs.dataPoints.clear()
            AutoAimCalcs.dataPoints.addAll(originalPoints)
            AutoAimCalcs.shooterEquation = originalEquation
        }
    }

    @Test
    fun `fits exact linear data`() = withDataPoints(
        AutoAimCalcs.ShotPoint(1.0, 1000),
        AutoAimCalcs.ShotPoint(2.0, 1200),
        AutoAimCalcs.ShotPoint(3.0, 1400)
    ) {
        AutoAimCalcs.generateShooterEquation()

        assertEquals(200.0, AutoAimCalcs.shooterEquation.first, 1e-9)
        assertEquals(800.0, AutoAimCalcs.shooterEquation.second, 1e-9)
    }

    @Test
    fun `fits two points exactly`() = withDataPoints(
        AutoAimCalcs.ShotPoint(10.0, 2000),
        AutoAimCalcs.ShotPoint(20.0, 2500)
    ) {
        AutoAimCalcs.generateShooterEquation()

        assertEquals(50.0, AutoAimCalcs.shooterEquation.first, 1e-9)
        assertEquals(1500.0, AutoAimCalcs.shooterEquation.second, 1e-9)
    }

    @Test
    fun `fits noisy data using least squares`() = withDataPoints(
        AutoAimCalcs.ShotPoint(1.0, 1010),
        AutoAimCalcs.ShotPoint(2.0, 1190),
        AutoAimCalcs.ShotPoint(3.0, 1410)
    ) {
        AutoAimCalcs.generateShooterEquation()

        assertEquals(200.0, AutoAimCalcs.shooterEquation.first, 1e-9)
        assertEquals(803.3333333333334, AutoAimCalcs.shooterEquation.second, 1e-9)
    }

    @Test
    fun `fits a horizontal line`() = withDataPoints(
        AutoAimCalcs.ShotPoint(10.0, 2000),
        AutoAimCalcs.ShotPoint(20.0, 2000),
        AutoAimCalcs.ShotPoint(30.0, 2000)
    ) {
        AutoAimCalcs.generateShooterEquation()

        assertEquals(0.0, AutoAimCalcs.shooterEquation.first, 1e-9)
        assertEquals(2000.0, AutoAimCalcs.shooterEquation.second, 1e-9)
    }

    @Test
    fun `fits a negative slope`() = withDataPoints(
        AutoAimCalcs.ShotPoint(10.0, 2500),
        AutoAimCalcs.ShotPoint(20.0, 2000),
        AutoAimCalcs.ShotPoint(30.0, 1500)
    ) {
        AutoAimCalcs.generateShooterEquation()

        assertEquals(-50.0, AutoAimCalcs.shooterEquation.first, 1e-9)
        assertEquals(3000.0, AutoAimCalcs.shooterEquation.second, 1e-9)
    }

    @Test
    fun `requires at least two points when empty`() = withDataPoints() {
        assertThrows(IllegalArgumentException::class.java) {
            AutoAimCalcs.generateShooterEquation()
        }
    }

    @Test
    fun `requires at least two points when given one point`() =
        withDataPoints(
            AutoAimCalcs.ShotPoint(10.0, 2000)
        ) {
            assertThrows(IllegalArgumentException::class.java) {
                AutoAimCalcs.generateShooterEquation()
            }
        }

    @Test
    fun `rejects points with identical distances`() = withDataPoints(
        AutoAimCalcs.ShotPoint(10.0, 2000),
        AutoAimCalcs.ShotPoint(10.0, 2200)
    ) {
        assertThrows(IllegalArgumentException::class.java) {
            AutoAimCalcs.generateShooterEquation()
        }
    }

    @Test
    fun `does not replace equation when there are too few points`() =
        withDataPoints(
            AutoAimCalcs.ShotPoint(10.0, 2000)
        ) {
            AutoAimCalcs.shooterEquation = Pair(25.0, 1000.0)

            assertThrows (IllegalArgumentException::class.java) {
                AutoAimCalcs.generateShooterEquation()
            }

            assertEquals(25.0, AutoAimCalcs.shooterEquation.first, 1e-9)
            assertEquals(1000.0, AutoAimCalcs.shooterEquation.second, 1e-9)
        }

    @Test
    fun `does not replace equation when distances are identical`() =
        withDataPoints(
            AutoAimCalcs.ShotPoint(10.0, 2000),
            AutoAimCalcs.ShotPoint(10.0, 2200)
        ) {
            AutoAimCalcs.shooterEquation = Pair(25.0, 1000.0)

            assertThrows(IllegalArgumentException::class.java) {
                AutoAimCalcs.generateShooterEquation()
            }

            assertEquals(25.0, AutoAimCalcs.shooterEquation.first, 1e-9)
            assertEquals(1000.0, AutoAimCalcs.shooterEquation.second, 1e-9)
        }

    //endregion
}