package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class Wait extends CommandRC {
    
    public Wait(CommandRC commandOnExit, double timeout) {
        super(-1, commandOnExit);
        setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }
}
