package org.ghrobotics.frc2019

import koma.extensions.get
import org.ghrobotics.frc2019.subsystems.flywheel.Flywheel
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.style.markers.SeriesMarkers

fun main() {
    val estimatedFlywheelStates = arrayListOf<Double>()
    val reference = arrayListOf<Double>()
    val inputs = arrayListOf<Double>()
    val times = arrayListOf<Double>()

    var t = 0.0

    while (t < 2) {
        val ref = if (t < 1) 523.5987749999999 else -523.5987749999999

        Flywheel.setClosedLoop(ref)
        Flywheel.update()

        estimatedFlywheelStates.add(Flywheel.x[0, 0])
        reference.add(ref)
        inputs.add(Flywheel.controller.voltage)
        times.add(t)

        t += 1 / 200.0
        Thread.sleep(5)
    }

    val chart1 = QuickChart.getChart(
        "Response",
        "T",
        "rad/s",
        "Estimated State",
        times.toDoubleArray(),
        estimatedFlywheelStates.toDoubleArray()
    )
    val series = chart1.addSeries("Reference", times.toDoubleArray(), reference.toDoubleArray())
    series.marker = SeriesMarkers.NONE

    val chart2 = QuickChart.getChart(
        "Control Effort",
        "T",
        "V",
        "Control Effort",
        times.toDoubleArray(),
        inputs.toDoubleArray()
    )

    SwingWrapper(chart1).displayChart()
    SwingWrapper(chart2).displayChart()
    Thread.sleep(100000)
}