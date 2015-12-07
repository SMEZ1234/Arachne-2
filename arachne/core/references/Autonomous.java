package redbacks.arachne.core.references;

import static redbacks.arachne.core.CommandBase.*;
import static redbacks.arachne.core.references.CommandList.*;
import static redbacks.arachne.core.references.RobotMap.*;
import redbacks.arachne.lib.commands.CommandHolder;
import redbacks.arachne.lib.commands.CommandRB;

/**
 * Autonomous holds all the autonomous functions used on the robot, and any autonomous specific commands.
 *
 * @author Sean Zammit
 */
public class Autonomous
{
	/** The size of the list of autonomous functions. */
	private static int noAutos = 1;
	
	/** The list of autonomous functions. Index zero should be left as cDoNothing. */
	public static CommandRB[] autonomous = new CommandRB[noAutos];
	
	//public static CommandHolder cAutoInsertNameHere = new CommandHolder();
	
	/**
	 * Called by OI. Set the sequence of actions run in autonomous here.
	 */
	public static void initAutonomous() {
		//Do nothing
		autonomous[0] = cDoNothing.c();
	}
}
