package org.ghrobotics.frc2019.control

import koma.extensions.get
import koma.mat
import koma.matrix.Matrix
import koma.zeros
import org.ghrobotics.frc2019.subsystems.flywheel.Flywheel
import org.ghrobotics.frc2019.subsystems.flywheel.FlywheelCoeffs
import org.ghrobotics.lib.mathematics.statespace.*

class FlywheelController {

    var u = mat[0.0]

    private val observerCoefficients = StateSpaceObserverCoeffs(1, 1, 1) { FlywheelCoeffs.L }
    private val plantCoefficients = StateSpacePlantCoeffs(1, 1, 1) {
        listOf(
            FlywheelCoeffs.A,
            FlywheelCoeffs.B,
            FlywheelCoeffs.C,
            FlywheelCoeffs.D
        )
    }
    private val controllerCoefficients = StateSpaceControllerCoeffs(1, 1, 1) {
        listOf(
            FlywheelCoeffs.K,
            FlywheelCoeffs.Kff,
            mat[-12.0],
            mat[+12.0]
        )
    }

    var reference = zeros(1, 1)
    var y = zeros(1, 1)

    val voltage get() = u[0, 0]

    private val plant = StateSpacePlant(plantCoefficients)
    private val controller = StateSpaceController(controllerCoefficients, plant)
    private val observer = StateSpaceObserver(observerCoefficients, plant)

    fun update() {
        observer.correct(controller.u, y)
        observer.predict(controller.u)

        controller.update(reference, observer.xHat)

        this.u = controller.u

        Flywheel.x = FlywheelCoeffs.A * Flywheel.x + FlywheelCoeffs.B * u
        Flywheel.y = FlywheelCoeffs.C * Flywheel.x + FlywheelCoeffs.D * u
    }
}