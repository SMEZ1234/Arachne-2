package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.digital.CheckBoolean;
import redbacks.arachne.lib.commands.CommandHolder;
import redbacks.arachne.lib.commands.CommandRB;
import redbacks.arachne.lib.subsystems.SubsystemRB;

/**
 * This Action will run a command populated with a list of provided actions, or a pre-existing command.
 * 
 * @author Sean Zammit
 */
public class ActionSequencer extends Action
{
	/** The command holder from which the command will be created. */
	public CommandHolder sequence;
	
	/**
	 * @param sequence A pre-existing command, that will be triggered by this action.
	 */
	public ActionSequencer(CommandHolder sequence) {
		super(new CheckBoolean(true));
		this.sequence = sequence;
	}
	
	/**
	 * @param requiredSystem The subsystem that this command requires, or null for none.
	 * @param actions The list of actions to be run in the command.
	 */
	public ActionSequencer(SubsystemRB requiredSystem, Action... actions) {
		this(new CommandHolder(requiredSystem, actions));
	}
	
	public void onFinish(CommandRB command) {
		sequence.c().start();
	}
}
