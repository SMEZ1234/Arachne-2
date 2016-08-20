package redbacks.arachne.core.references;

import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.commands.CommandSetup;

/**
 * AutonomousStart holds all the autonomous functions used on the robot, and any autonomous specific commands.
 *
 * @author Sean Zammit
 */
public class AutonomousStart
{
	/**
	 * Set the sequence of actions run in autonomous here.
	 */
	public static CommandBase getAutonomous(int autoNumber) {
		switch(autoNumber) {
		//Example autonomous
		//case(1): return createAuto(
		//				new ActionExample(),
		//				new ActionExample(),
		//				new ActionExample());
		default: return createAuto(new AcDoNothing());
		}
	}
	
	public static CommandBase createAuto(Action... actions) {
		return new CommandSetup(null, actions).c();
	}
}
