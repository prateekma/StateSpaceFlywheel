package org.ghrobotics.frc2019

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import org.ghrobotics.frc2019.subsystems.Flywheel
import org.ghrobotics.frc2019.subsystems.SubsystemHandler
import org.ghrobotics.lib.commands.FalconSubsystem

class Robot : TimedRobot() {
    init {
        +Flywheel
    }

    override fun robotInit() {
        SubsystemHandler.lateInit()
    }

    override fun disabledInit() {
        SubsystemHandler.zeroOutputs()
    }

    override fun autonomousInit() {
        SubsystemHandler.autoReset()
    }

    override fun teleopInit() {
        SubsystemHandler.teleopReset()
    }

    override fun robotPeriodic() {
        Scheduler.getInstance().run()
    }

    operator fun FalconSubsystem.unaryPlus() {
        SubsystemHandler.addSubsystem(this)
    }
}
