package edu.wpi.first.wpilibj.base.commands.drive;

import edu.wpi.first.wpilibj.superclasses.CommandBaseRedCrusade;

/**
 * @author Sean Zammit
 */
public class TimedMove extends CommandBaseRedCrusade {
    
    double speed;
    
    public TimedMove(int requiredSystem, double time, double sp, CommandBaseRedCrusade com) {
        super(requiredSystem);
        setTimeout(time);
        speed = sp;
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
}
