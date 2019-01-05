package redbacks.arachne.lib.actions.drive;

import redbacks.arachne.lib.motors.CtrlDrivetrain;
import redbacks.arachne.lib.motors.CtrlMecanumDrivetrain;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * Similar to the typical RobotMap class, this holds a list of static variables to configure basic drivetrain settings.
 * These are used to provide basic driving actions.
 *
 * @author Sean Zammit
 */
public class DriveSettings
{
	/** Your standard drivetrain, which will be controlled by the actions in this package for differential drive systems. */
	public static CtrlDrivetrain drivetrain = null;
	
	/** Your mecanum drivetrain. Currently unused. */
	public static CtrlMecanumDrivetrain mecanumDrivetrain = null;
	
	/** The gyro used to determine the heading of your robot */
	public static NumericSensor gyro = null;
	
	/** The rate at which drivetrain values be modified to correct misalignment per unit of measurement on the gyro. */
	public static double gyroCorrection = 0.03;
	
	/** The acceptable number of units of measurement on the gyro that the robot can be from its target to consider itself aligned. */
	public static double gyroTolerance = 3;
}
