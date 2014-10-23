package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class CommandWait extends CommandRC {
    
    public CommandWait(CommandRC commandOnExit, double timeout) {
        super(-1, commandOnExit);
        setTimeout(timeout);
    }

    //Set up what the robot will do while this command is running.
    protected void execute() {
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return isTimedOut();
    }
}
