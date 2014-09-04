package edu.wpi.first.wpilibj.base.commands.autonomous;

import edu.wpi.first.wpilibj.superclasses.CommandBaseRedCrusade;

/**
 *
 * @author Author
 */
public class Autonomous extends CommandBaseRedCrusade {

    public Autonomous(CommandBaseRedCrusade com) {
        super(-1, com);
    }
    
    //Set up what the robot will do while this command is running.
    protected void execute() {
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
        if(command != null) {
            command.start();
        }
    }
}
