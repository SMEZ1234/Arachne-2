package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.templates.*;
import edu.wpi.first.wpilibj.base.RobotMap;
import edu.wpi.first.wpilibj.base.motors.MotorGroup;
import edu.wpi.first.wpilibj.superclasses.CommandRC;

/**
 *
 * @author Sean Zammit
 */
public class CommandMotorGroup extends CommandRC {

    public MotorGroup motorGroup;
    
    private CommandMotorGroup(int requiredSystem, MotorGroup mg) {
        super(requiredSystem);
        motorGroup = mg;
    }
    
    //Set up what the robot will do while this command is running.
    protected void execute() {
        for(int a = 0; a < motorGroup.motors.length; a++) {
            motorGroup.motors[a].set(motorGroup.speed);
        }
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return false;
    }
}
