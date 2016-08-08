package redbacks.arachne.lib.commands;

import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.subsystems.SubsystemBase;

/**
 * Holds each command. It creates a new instance of the command each time the command is used.
 * 
 * @author Sean Zammit
 */
public class CommandSetup
{
	SubsystemBase subsystem;
	Action[] actions;
	
	/**
	 * CommandSetup is used to create Action commands.
	 * The c() method creates a new instance of CommandBase based on the parameters in this constructor.
	 * 
	 * @param requiredSystem The subsystem on which the command is run. Null for no subsystem.
	 * @param actions The sequence of actions that the command will perform.
	 */
	public CommandSetup(SubsystemBase requiredSystem, Action... actions) {
		this.subsystem = requiredSystem;
		this.actions = actions;
	}
	
	/**
	 * Creates a new instance of the command.
	 * This will ensure that the command functions the same way each time, and that all variables are reset.
	 * 
	 * @return The new CommandBase instance.
	 */
	public CommandBase c() {
		return new CommandBase(subsystem, actions);
	}
}
