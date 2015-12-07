package redbacks.arachne.core.references;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.actions.ActionDoNothing;
import redbacks.arachne.lib.actions.ActionInterrupt;
import redbacks.arachne.lib.commands.CommandHolder;

/**
 * Holds all non subsystem specific commands.
 * 
 * @author Sean Zammit
 */
public class CommandList
{
	public static CommandHolder
		cDoNothing = newCom(new ActionDoNothing()),
		cInterrupt = newCom(new ActionInterrupt.KillAllSubsystems());
	
	/**
	 * Creates a new command from a list of actions that requires no subsystem.
	 * Commands which require subsystems should be created inside that subsystem's file.
	 * 
	 * @param actions The list of actions to be run in the command.
	 * @return The holder of the command. Use .c() to access the command.
	 */
	public static CommandHolder newCom(Action... actions) {
		return new CommandHolder(null, actions);
	}
}
