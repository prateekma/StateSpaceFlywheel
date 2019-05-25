package org.ghrobotics.frc2019

import org.junit.Assert
import org.junit.Test

class FlywheelNativeUnitModelTest {

    private val model = Constants.kFlywheelNativeUnitModel

    @Test
    fun testAngleRollover1() {
        val rawSensorVelocity = 6000.0
        Assert.assertEquals(6000.0 / 1440.0 * 2 * Math.PI, model.fromNativeUnitVelocity(rawSensorVelocity), 0.1)
    }
}