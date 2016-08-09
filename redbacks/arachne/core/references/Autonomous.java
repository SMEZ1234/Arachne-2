package redbacks.arachne.core.references;

import static redbacks.arachne.core.references.CommandList.*;
import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.commands.CommandSetup;

/**
 * Autonomous holds all the autonomous functions used on the robot, and any autonomous specific commands.
 *
 * @author Sean Zammit
 */
public class Autonomous
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
		default: return doNothing.c();
		}
	}
	
	public static CommandBase createAuto(Action... actions) {
		return new CommandSetup(null, actions).c();
	}
}
