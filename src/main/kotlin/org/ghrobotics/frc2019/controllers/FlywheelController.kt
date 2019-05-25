package org.ghrobotics.frc2019.controllers

import koma.extensions.get
import koma.extensions.set
import koma.mat
import koma.zeros
import org.ghrobotics.lib.mathematics.statespace.*

class FlywheelController {

    private val observerCoefficients = StateSpaceObserverCoeffs(2, 1, 1) { FlywheelCoeffs.L }
    private val plantCoefficients = StateSpacePlantCoeffs(2, 1, 1) {
        listOf(
            FlywheelCoeffs.A,
            FlywheelCoeffs.B,
            FlywheelCoeffs.C,
            FlywheelCoeffs.D
        )
    }
    private val controllerCoefficients = StateSpaceControllerCoeffs(2, 1, 1) {
        listOf(
            FlywheelCoeffs.K,
            FlywheelCoeffs.Kff,
            mat[-12.0],
            mat[+12.0]
        )
    }

    private val plant = StateSpacePlant(plantCoefficients)
    private val controller = StateSpaceController(controllerCoefficients, plant)
    private val observer = StateSpaceObserver(observerCoefficients, plant)

    private var reference = zeros(2, 1)
    private var y = zeros(1, 1)
    private var u = mat[0.0]

    /**
     * Returns the input voltage in Volts
     */
    val voltage get() = u[0, 0]

    /**
     * Sets the controller reference
     * @param velocity The desired velocity in rad/s
     */
    fun setReference(velocity: Double) {
        reference[0, 0] = velocity
    }

    /**
     * Set the measured velocity
     * @param velocity The measured velocity in rad/s
     */
    fun setMeasuredVelocity(velocity: Double) {
        y[0, 0] = velocity
    }

    /**
     * Update the controller
     */
    fun update() {
        observer.correct(controller.u, y)
        observer.predict(controller.u)

        controller.update(reference, observer.xHat)

        this.u = controller.u
    }
}