package org.ghrobotics.frc2019

import edu.wpi.first.wpilibj.TimedRobot
import org.ghrobotics.frc2019.subsystems.Flywheel
import org.ghrobotics.frc2019.subsystems.SubsystemHandler
import org.ghrobotics.lib.commands.FalconSubsystem

class Robot : TimedRobot() {
    init {
        +Flywheel
    }

    override fun robotInit() {
        Flywheel.enable()
    }

    override fun disabledInit() {
        Flywheel.zeroOutputs()
    }

    operator fun FalconSubsystem.unaryPlus() {
        SubsystemHandler.addSubsystem(this)
    }
}
