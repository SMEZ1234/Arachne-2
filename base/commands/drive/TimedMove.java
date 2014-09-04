package edu.wpi.first.wpilibj.base.commands.drive;

import edu.wpi.first.wpilibj.base.commands.CommandBase;

/**
 * @author Sean Zammit
 */
public class TimedMove extends CommandBase {
    
    double speed;
    CommandBase command;
    
    public TimedMove(double time, double sp, CommandBase com) {
        requires(driver);
        setTimeout(time);
        speed = sp;
        command = com;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {        
        double rotation = 0;
                
        driver.driveFront.arcadeDrive(speed, rotation);
        driver.driveRear.arcadeDrive(speed, rotation);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut(); 
    }

    // Called once after isFinished returns true
    protected void end() {
        if(command != null)
        {
            command.start();
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
