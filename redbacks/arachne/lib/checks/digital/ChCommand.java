package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.Check.CheckDigital;
import redbacks.arachne.lib.commands.CommandBase;

/**
 * Checks whether a command is complete.
 * Useful when waiting for a sequence to be complete before starting the next action.
 * 
 * @author Sean Zammit
 */
public class ChCommand extends CheckDigital
{
	CommandBase command;

	/**
	 * Constructor for a check that will return true when a command has finished running.
	 * 
	 * @param command The command being checked.
	 */
	public ChCommand(CommandBase command) {
		super(true);
		this.command = command;
	}

	public boolean isDone() {
		return !command.isRunning();
	}
}