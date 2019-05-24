package org.ghrobotics.frc2019.subsystems.flywheel

import koma.end
import koma.mat
import org.ghrobotics.frc2019.control.FlywheelController

object Flywheel {
    // SUBSYSTEM LOGIC HERE

    private val periodicIO = PeriodicIO()

    private var wantedState = State.Nothing
    private var currentState = State.Nothing

    // Internal Simulation Model, won't exist in real life.
    var x = mat[0.0 end 0.0]
    var y = mat[0.0]

    val controller = FlywheelController()

    fun setClosedLoop(angularVelocity: Double) {
        wantedState = State.ClosedLoop
        periodicIO.demand = angularVelocity
    }

    fun zeroOutputs() {
        wantedState = State.Nothing
        periodicIO.demand = 0.0
    }

    fun update() {
        controller.y = this.y
        controller.update()
        controller.reference = mat[periodicIO.demand end 0.0]

        // motor.setVoltage(controller.voltage)
        if (currentState != wantedState) currentState = wantedState
    }

    private class PeriodicIO {
        var demand = 0.0
    }

    private enum class State {
        ClosedLoop, Nothing
    }
}

