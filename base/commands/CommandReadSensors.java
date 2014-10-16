package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.base.OI;
import edu.wpi.first.wpilibj.base.subsystems.SubsystemRC;
import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class CommandReadSensors extends CommandRC {
    
    public CommandReadSensors(int requiredSystem) {
        super(requiredSystem);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Put any code here needed to handle readings from sensors.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
