
package org.firstinspires.ftc.teamcode

import android.util.Log
import com.bylazar.telemetry.PanelsTelemetry
import com.pedropathing.math.Pose
import com.qualcomm.robotcore.util.RobotLog
import org.firstinspires.ftc.robotcore.external.Telemetry
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

object Logger {
    private val panelsTelemetry = PanelsTelemetry.telemetry

    lateinit var loggerTelemetry: Telemetry

    private val writer: BufferedWriter

    init {
        val file = File("/sdcard/FIRST/log.csv")
        file.parentFile?.mkdirs()

        writer = BufferedWriter(FileWriter(file, false))

        writer.write(
            "Time,X,Y,Heading," +
                    "TurretTarget,TurretActual,ShooterTarget," +
                    "ShooterActual,ShooterPower"
        )
        writer.newLine()
    }

    fun setTelemetry(telemetry: Telemetry) {
        Logger.loggerTelemetry = telemetry
    }

    fun addData(caption: String, value: Any) {
        panelsTelemetry.addData(caption, value)
    }

    fun update() {
        panelsTelemetry.update(loggerTelemetry)
    }

    fun log(
        time: Long,
        pose: Pose,
        turretTarget: Double,
        turretActual: Double,
        shooterTarget: Double,
        shooterActual: Double,
        shooterPower: Double
    ) {
        writer.write(
            "$time,$pose" +
                    "$turretTarget,$turretActual,$shooterTarget," +
                    "$shooterActual,$shooterPower"
        )
        writer.newLine()
        RobotLog.d("~Log > Pose: $pose")
        RobotLog.d("~Log > Turret Target: $turretTarget")
        RobotLog.d("~Log > Turret Actual: $turretActual")
        RobotLog.d("~Log > Shooter Target: $shooterTarget")
        RobotLog.d("~Log > Shooter Actual: $shooterActual")
        RobotLog.d("~Log > Shooter Power: $shooterPower")
    }

    fun close() {
        writer.flush()
        writer.close()
    }
}
