package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check.CheckDigital;

/**
 * Checks whether an action is complete.
 * May be useful when waiting for various actions on a subsystem to be complete before starting the next action.
 * 
 * @author Sean Zammit
 */
public class ChAction extends CheckDigital {
	Action action;
	
	/**
	 * @param command The command that the check is waiting for.
	 */
	public ChAction(Action action) {
		super(true);
		this.action = action;
	}

	public boolean isDone() {
		return action.isRunning != type;
	}
}