package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class Interrupt extends CommandRC {
    
    public Interrupt() {
        super(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        //Hasn't been tested.
        for(int a = 0; a < subsystemList.length; a++) {
            subsystemList[a].getCurrentCommand().cancel();
        }
    }
}
