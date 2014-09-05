package edu.wpi.first.wpilibj.base.commands.autonomous;

import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class Autonomous extends CommandRC {

    public Autonomous(CommandRC com) {
        super(-1, com);
    }
    
    //Set up what the robot will do while this command is running.
    protected void execute() {
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
        if(command != null) {
            command.start();
        }
    }
}
