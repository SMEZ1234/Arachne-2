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
	/** The list of autonomous functions. */
	public static CommandBase[] autonomous = new CommandBase[2];
	
	/**
	 * Called by OI. Set the sequence of actions run in autonomous here.
	 */
	public static void initAutonomous() {
		//Do nothing
		autonomous[0] = doNothing.c();
		
		//Example secondary autonomous
		//autonomous[1] = createAuto(
		//		new ActionExample(),
		//		new ActionExample(),
		//		new ActionExample()
		//);
	}
	
	public static CommandBase createAuto(Action... actions) {
		return new CommandSetup(null, actions).c();
	}
}
