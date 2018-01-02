package redbacks.arachne.core.api;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.core.api.RobotAPI.Order;

/**
 * This is the superclass that enables other modules to add to the functionality of {@link ArachneRobot} without overriding either it or the user-created Robot class.
 * Though teams are welcome to use its functionality, you can get the same results by just extending ArachneRobot.
 *
 * @author Sean Zammit
 */
public class RobotExtender
{
	/**
	 * Superconstructor for classes adding to {@link ArachneRobot}'s functionality. Adds the class to a list to be checked for execution.
	 *
	 * @param order When the class's methods should be run. See {@link RobotAPI.Order}.
	 */
	public RobotExtender(Order order) {
		RobotAPI.addExtension(this, order);
	}
	
	/** Called once when the robot code starts. Replacement for {@link ArachneRobot#robotInit()}. */
	public void initialiseRobot() {}

	/** Called once when autonomous starts. Replacement for {@link ArachneRobot#autonomousInit()}. */
	public void initialiseAuto() {}

	/** Called repeatedly during autonomous. Replacement for {@link ArachneRobot#autonomousPeriodic()}. */
	public void executeAuto() {}

	/** Called once when teleop starts. Replacement for {@link ArachneRobot#teleopInit()}. */
	public void initialiseTeleop() {}

	/** Called repeatedly during teleop. Replacement for {@link ArachneRobot#teleopPeriodic()}. */
	public void executeTeleop() {}

	/** Called once when the robot is disabled. Replacement for {@link ArachneRobot#disabledInit()}. */
	public void initialiseDisabled() {}

	/** Called repeatedly while the robot is disabled. Replacement for {@link ArachneRobot#disabledPeriodic()}. */
	public void executeDisabled() {}
}
