package org.ghrobotics.frc2019.controllers

import org.ghrobotics.lib.mathematics.linalg.*

object FlywheelCoeffs {

    val A = mat(`2`, `2`).fill(
        0.997502463097695, 0.6216972081698791,
        0.000000000000000, 0.0000000000000000
    )

    val B = mat(`2`, `1`).fill(
        0.6155494833981922,
        0.0000000000000000
    )

    val C = mat(`1`, `2`).fill(
        1.0, 0.0
    )

    val D = mat(`1`, `1`).fill(
        0.0
    )

    val K = mat(`1`, `2`).fill(
        0.8654801449595761, 1.0
    )

    val Kff = mat(`1`, `2`).fill(
        0.6185642626159451, 0.0
    )

    val L = mat(`2`, `1`).fill(
        0.999900019945138,
        0.000000000000000
    )
}
