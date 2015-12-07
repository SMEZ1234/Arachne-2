package redbacks.arachne.core.references;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Talon SRX CAN IDs
	//Start at 2.
	public static final int
	    driveMotorLID = 2,
	    driveMotorRID = 3;
    
    //Solenoids
    public static final int
    	shifterID = 0;
}