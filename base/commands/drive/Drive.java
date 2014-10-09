package edu.wpi.first.wpilibj.base.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.base.OI;
import edu.wpi.first.wpilibj.base.subsystems.SubsystemRC;
import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class Drive extends CommandRC {
    
    public Drive(int requiredSystem) {
        super(requiredSystem);
    }

    Joystick primaryStick = OI.stickDriver;
    double stickX;
    double stickY;
    double valueZ;

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
}
