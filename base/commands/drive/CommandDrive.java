package edu.wpi.first.wpilibj.base.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.base.OI;
import edu.wpi.first.wpilibj.base.subsystems.SubsystemRC;
import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class CommandDrive extends CommandRC {
    
    public CommandDrive(int requiredSystem) {
        super(requiredSystem);
    }

    Joystick primaryStick = OI.stickDriver;
    double stickX;
    double stickY;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(OI.pivotSwitch.get()) {
            driver.driveFront.arcadeDrive(0, primaryStick.getTwist());
            driver.driveRear.arcadeDrive(0, primaryStick.getTwist());
        }
        else {
            stickX = primaryStick.getX();
            stickY = primaryStick.getY();
        
            if(OI.trigger.get()) {
                stickY /= 2;
            }
            stickX *= Math.abs(stickX);
        
            double speed = -stickY;
            double rotation = -stickX;
                
            driver.driveFront.arcadeDrive(speed, rotation);
            driver.driveRear.arcadeDrive(speed, rotation);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
