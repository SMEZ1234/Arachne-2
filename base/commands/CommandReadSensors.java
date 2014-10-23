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

    //Set up what the robot will do while this command is running.
    protected void execute() {
        //Put any code here needed to handle readings from sensors.
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return false;
    }
}
