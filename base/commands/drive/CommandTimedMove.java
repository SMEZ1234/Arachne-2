package edu.wpi.first.wpilibj.base.commands.drive;

import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 * @author Sean Zammit
 */
public class CommandTimedMove extends CommandRC {
    
    double speed;
    
    public CommandTimedMove(int requiredSystem, double time, double sp, CommandRC com) {
        super(requiredSystem);
        setTimeout(time);
        speed = sp;
    }

    //Set up what the robot will do while this command is running.
    protected void execute() {        
        double rotation = 0;
                
        driver.driveFront.arcadeDrive(speed, rotation);
        driver.driveRear.arcadeDrive(speed, rotation);
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return isTimedOut(); 
    }
}
