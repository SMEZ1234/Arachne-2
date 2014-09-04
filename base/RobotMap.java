package edu.wpi.first.wpilibj.base;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //Motors
    public static final int 
        driveMotorFL = 3,
        driveMotorFR = 1,
        driveMotorRL = 4,
        driveMotorRR = 2;
    
    //Sensors
    public static final int
        exampleSensor = 1;
    
    //Put all 'magic numbers' here. Just putting them in the code makes it hard to fix things when they go wrong.
}