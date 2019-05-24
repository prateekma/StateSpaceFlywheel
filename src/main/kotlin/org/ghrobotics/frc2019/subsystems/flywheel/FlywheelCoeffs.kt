package org.ghrobotics.frc2019.subsystems.flywheel

import koma.end
import koma.mat

object FlywheelCoeffs {

    val A = mat[0.997502463097695, 0.6216972081698791 end
                0.0, 0.0]

    val B = mat[0.6155494833981922 end
                0.0]

    val C = mat[1.0, 0.0]

    val D = mat[0.0]

    val K = mat[0.8654801449595761, 1.0]

    val Kff = mat[0.6185642626159451, 0.0]

    val L = mat[0.999900019945138 end
                0.0]
}