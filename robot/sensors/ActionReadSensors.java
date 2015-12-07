package redbacks.robot.sensors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.CheckNever;
import redbacks.arachne.lib.commands.CommandRB;

/**
 * The action that is constantly run by the sensor subsystem.
 * Fill it with sensor data.
 * 
 * @author Sean Zammit
 */
public class ActionReadSensors extends Action
{
	/** The number of loops on the robot */
	static int tick = 0;

	public ActionReadSensors() {
		super(new CheckNever());
	}
	
	public void runAction(CommandRB command) {
		SmartDashboard.putNumber("Tick", tick++);
	}
}
