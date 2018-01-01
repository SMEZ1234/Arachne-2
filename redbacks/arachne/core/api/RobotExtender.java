package redbacks.arachne.core.api;

import redbacks.arachne.core.api.RobotAPI.Order;

public class RobotExtender
{
	public RobotExtender(Order order) {
		RobotAPI.addExtension(this, order);
	}
	
	/** Called once when the robot code starts. Replacement for {@link #robotInit() robotInit}. */
	public void initialiseRobot() {}

	/** Called once when autonomous starts. Replacement for {@link #autonomousInit() autonomousInit}. */
	public void initialiseAuto() {}

	/** Called repeatedly during autonomous. Replacement for {@link #autonomousPeriodic() autonomousPeriodic}. */
	public void executeAuto() {}

	/** Called once when teleop starts. Replacement for {@link #teleopInit() teleopInit}. */
	public void initialiseTeleop() {}

	/** Called repeatedly during teleop. Replacement for {@link #teleopPeriodic() teleopPeriodic}. */
	public void executeTeleop() {}

	/** Called once when the robot is disabled. Replacement for {@link #disabledInit() disabledInit}. */
	public void initialiseDisabled() {}

	/** Called repeatedly while the robot is disabled. Replacement for {@link #disabledPeriodic() disabledPeriodic}. */
	public void executeDisabled() {}
}
