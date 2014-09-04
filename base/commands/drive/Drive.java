package edu.wpi.first.wpilibj.base.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.base.OI;
import edu.wpi.first.wpilibj.superclasses.CommandBaseRedCrusade;

/**
 *
 * @author Developer
 */
public class Drive extends CommandBaseRedCrusade {
    
    public Drive(int requiredSystem) {
        super(requiredSystem);
    }

    Joystick primaryStick = OI.stickDriver;
    double stickX;
    double stickY;
    double valueZ;
        
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        stickX = primaryStick.getX();
        stickY = primaryStick.getY();
        
        if(OI.trigger.get()) {
            stickY /= 2;
        }
        stickX *= Math.abs(stickX);        
        
        valueZ = primaryStick.getZ();
        
        double speed = -stickY;
        double rotation = -stickX;
                
        driver.driveFront.arcadeDrive(speed, rotation);
        driver.driveRear.arcadeDrive(speed, rotation);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
