package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class CommandInterrupt extends CommandRC {
    
    public CommandInterrupt() {
        super(-1);
    }

    //Set up what the robot will do while this command is running.
    protected void execute() {
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return true;
    }

    //Called once after the command is finished.
    protected void end() {
        //Hasn't been tested.
        for(int a = 0; a < subsystemList.length; a++) {
            subsystemList[a].getCurrentCommand().cancel();
        }
    }
}
