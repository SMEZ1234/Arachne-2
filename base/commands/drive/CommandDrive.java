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

    //Set up what the robot will do while this command is running.
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

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return false;
    }
}
