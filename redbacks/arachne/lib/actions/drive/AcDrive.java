package redbacks.arachne.lib.actions.drive;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;

/**
 * Teleop drivetrain control.
 * With so many different ways to control a robot, this action mainly exists to provide a variable to control whether
 * the robot should drive forwards or backwards. The value of this variable is taken from the SmartDashboard and passed
 * into a functional interface that determines actual functionality.
 *
 * @author Sean Zammit
 */
public class AcDrive extends Action
{
	Consumer<Boolean> drive;
	
	/**
	 * Constructor for an action to pass driver inputs to the drivetrain.
	 * 
	 * @param drive A functional interface that accepts a boolean value representing whether the robot should have its
	 * 		direction inverted. You have to specify how your inputs will control the robot here.
	 */
	public AcDrive(Consumer<Boolean> drive) {
		super(new ChFalse());
		this.drive = drive;
	}
	
	public void onRun() {
		// Change drive direction to opposite side
		boolean invertDrive = SmartDashboard.getBoolean("Invert Drive", false);
		SmartDashboard.putBoolean("Invert Drive", invertDrive);
		
		if(!ArachneRobot.isIndivDriveControl) drive.accept(invertDrive);
	}
}
