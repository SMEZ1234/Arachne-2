package redbacks.arachne.core.references;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.actions.AcSeq;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.commands.CommandSetup;

/**
 * AutoStart should be the superclass for your Auto class, which holds all of your autonomous routines.
 *
 * @author Sean Zammit
 */
public class AutoStart
{
	/**
	 * Set the sequence of actions run in autonomous here.
	 * You should override this method in your Auto class, and return it from the {@link ArachneRobot#getAutonomous(int) getAutonomous(int)} method in your robot class.
	 * 
	 * @param autoNumber Determines which autonomous routine you want to run.
	 * @return The autonomous command to run.
	 */
	public static CommandBase getAutonomous(int autoNumber) {
		switch(autoNumber) {
			//Example autonomous
			//case(1): return createAuto(
			//				new ActionExample(),
			//				new ActionExample(),
			//				new ActionExample());
			default: return null;
		}
	}

	/**
	 * Creates a new command for the autonomous sequence.
	 * 
	 * @param actions The actions that the command should run.
	 * @return A new instance of {@link CommandBase} containing the list of actions to be run in autonomous.
	 */
	public static CommandBase createAuto(Action... actions) {
		return new CommandSetup(null, new AcSeq.Parallel(actions)).c();
	}
}
