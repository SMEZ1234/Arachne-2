package edu.wpi.first.wpilibj.base.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.base.RobotMap;

/**
 * @author Author
 */
public class Driver extends SubsystemRC {
    //Set up motors here. Make sure to use the RobotMap.
    public RobotDrive driveFront = new RobotDrive(RobotMap.driveMotorFL, RobotMap.driveMotorFR);
    public RobotDrive driveRear = new RobotDrive(RobotMap.driveMotorRL, RobotMap.driveMotorRR);
        
    public Driver(int id) {
        super(id);
    }
    
    public void onInit() {
        //Perform anything that needs to be done before the subsystem is used here. 
        //Do not start sensors. Do that in SubsystemRC.initSensors().
    }
    
    //Set up functions using the motors here.
}
