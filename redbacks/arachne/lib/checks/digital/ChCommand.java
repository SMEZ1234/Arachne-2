package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.Check.CheckDigital;
import redbacks.arachne.lib.commands.CommandBase;

/**
 * Checks whether a command is complete.
 * May be useful when waiting for various actions on a subsystem to be complete before starting the next action.
 * 
 * @author Sean Zammit
 */
public class ChCommand extends CheckDigital {
	CommandBase command;
	
	/**
	 * @param command The command that the check is waiting for.
	 */
	public ChCommand(CommandBase command) {
		super(true);
		this.command = command;
	}

	public boolean isDone() {
		return command.isRunning() != type;
	}
}