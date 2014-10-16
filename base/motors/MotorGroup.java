package edu.wpi.first.wpilibj.base.motors;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.helpers.ArrayHelper;

/**
 *
 * @author Sean Zammit
 */
public class MotorGroup { 
    
    public SpeedController[] motors = new SpeedController[0];
    public float speed = 0;
    public float position = 0;
    public boolean requiresUpdate = false;
    
    public MotorGroup() {}
    
    public MotorGroup(float pos) {
        position = pos;
    }
    
    public MotorGroup addMotor(SafePWM motor) {
        motors = (SpeedController[]) ArrayHelper.combineLists(motors, motor);        
        return this;
    }
    
    public MotorGroup setSpeed(float sp) {
        speed = sp;
        return this;
    }
    
    public MotorGroup setPosition(float pos) {
        position = pos;
        return this;
    }
    
    public MotorGroup setToUpdate() {
        requiresUpdate = true;
        return this;
    }
    
    public MotorGroup setToUpdate(float pos) {
        position = pos;
        return setToUpdate();
    }
}
