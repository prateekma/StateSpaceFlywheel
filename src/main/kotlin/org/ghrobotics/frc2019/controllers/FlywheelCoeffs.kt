package org.ghrobotics.frc2019.controllers

import frc.team4069.keigen.*
import org.ghrobotics.lib.mathematics.statespace.StateSpaceControllerCoeffs
import org.ghrobotics.lib.mathematics.statespace.StateSpaceObserverCoeffs
import org.ghrobotics.lib.mathematics.statespace.StateSpacePlantCoeffs

object FlywheelCoeffs {
    val plantCoeffs = StateSpacePlantCoeffs(
        `2`, `1`, `1`,
        A = mat(`2`, `2`).fill(
            0.997502463097695, 0.6216972081698791,
            0.000000000000000, 0.0000000000000000
        ),
        B = mat(`2`, `1`).fill(
            0.6155494833981922,
            0.0000000000000000
        ),
        C = mat(`1`, `2`).fill(
            1.0, 0.0
        ),
        D = mat(`1`, `1`).fill(
            0.0
        )
    )

    val controllerCoeffs = StateSpaceControllerCoeffs<`2`, `1`, `1`>(
        K = mat(`1`, `2`).fill(
            0.8654801449595761, 1.0
        ),
        Kff = mat(`1`, `2`).fill(
            0.6185642626159451, 0.0
        ),
        UMin = vec(`1`).fill(-12.0),
        UMax = vec(`1`).fill(+12.0)
    )

    val observerCoeffs = StateSpaceObserverCoeffs<`2`, `1`, `1`>(
        K = mat(`2`, `1`).fill(
            0.999900019945138,
            0.000000000000000
        )
    )
}
