package redbacks.arachne.lib.actions.drive;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;

/**
 * An action to drive at a specified heading.
 * The drivetrain, gyro and correction value used come from {@link DriveSettings}.
 * 
 * @author Sean Zammit
 */
public class AcDriveDirection extends Action
{
	double speed, heading;
	
	/**
	 * Constructor for an action to drive at a specific heading.
	 * 
	 * @param check The condition that will finish the action.
	 * @param speed The desired motor speed.
	 * @param heading The intended heading to drive at.
	 */
	public AcDriveDirection(Check check, double speed, double heading) {
		super(check);
		this.speed = speed;
		this.heading = heading;
	}
	
	public void onStart() {
		ArachneRobot.isIndivDriveControl = false;
	}

	public void onRun() {
		double correction = (DriveSettings.gyro.get() - heading) * DriveSettings.gyroCorrection;
		
		DriveSettings.drivetrain.tankDrive(speed - correction, speed + correction);
	}
}