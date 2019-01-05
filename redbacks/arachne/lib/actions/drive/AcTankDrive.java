package redbacks.arachne.lib.actions.drive;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;

/**
 * A basic bang-bang drivetrain movement until a specified check condition is met.
 * The drivetrain used comes from {@link DriveSettings}.
 *
 * @author Sean Zammit
 */
public class AcTankDrive extends Action
{
	double left, right;
	
	/**
	 * Constructor for an action to individually set motor speeds for the left and right sides of the drivetrain.
	 * 
	 * @param check The condition that will finish the action.
	 * @param left The speed for the left motors.
	 * @param right The speed for the right motors.
	 */
	public AcTankDrive(Check check, double left, double right) {
		super(check);
		this.left = left;
		this.right = right;
	}
	
	public void onStart() {
		ArachneRobot.isIndivDriveControl = false;
	}

	public void onRun() {
		DriveSettings.drivetrain.tankDrive(left, right);
	}
}