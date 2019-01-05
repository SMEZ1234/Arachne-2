package redbacks.arachne.lib.actions.drive;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;

/**
 * A basic bang-bang turn to a specified heading.
 * The drivetrain, gyro and angle tolerance used come from {@link DriveSettings}.
 * 
 * @author Sean Zammit
 */
public class AcTankTurn extends Action
{
	double heading, speed;
	
	/**
	 * Constructor for an action to turn to a specified heading.
	 * 
	 * @param heading The desired heading.
	 * @param speed The speed at which the robot should turn.
	 */
	public AcTankTurn(double heading, double speed) {
		super(new ChFalse());
		
		this.heading = heading;
		this.speed = speed;
	}
	
	/**
	 * Constructor for an action to turn to a specified heading.
	 * Speed of turning is set to maximum.
	 * 
	 * @param heading The desired heading.
	 */
	public AcTankTurn(double heading) {
		this(heading, 1);
	}
	
	public void onStart() {
		ArachneRobot.isIndivDriveControl = false;
	}
	
	public void onRun() {
		double turnSpeed = (heading > DriveSettings.gyro.get() ? 1 : -1) * speed;
		
		DriveSettings.drivetrain.tankDrive(turnSpeed, -turnSpeed);
	}
	
	public boolean isDone() {
		return Math.abs(DriveSettings.gyro.get() - heading) < DriveSettings.gyroTolerance;
	}
}