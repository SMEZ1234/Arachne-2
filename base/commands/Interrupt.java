package edu.wpi.first.wpilibj.base.commands;

/**
 *
 * @author Sean Zammit
 */
public class Interrupt extends CommandBase {
    
    public Interrupt() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
