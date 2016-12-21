package redbacks.arachne.lib.commands;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.Action;

/**
 * Used to create instances of commands.
 * 
 * @author Sean Zammit
 */
public class CommandSetup
{
	SubsystemBase subsystem;
	Action[] actions;

	/**
	 * Constructor for an instance of CommandSetup that can be used to create commands.
	 * 
	 * @param requiredSystem The subsystem on which the command is run. Null for no subsystem requirement.
	 * @param actions The sequence of actions that the command will perform.
	 */
	public CommandSetup(SubsystemBase requiredSystem, Action... actions) {
		this.subsystem = requiredSystem;
		this.actions = actions;
	}

	/**
	 * Creates a new CommandBase instance with the required subsystem and actions specified in the CommandSetup constructor.
	 * 
	 * @return The new CommandBase instance.
	 */
	public CommandBase c() {
		return new CommandBase(subsystem, actions);
	}
}
