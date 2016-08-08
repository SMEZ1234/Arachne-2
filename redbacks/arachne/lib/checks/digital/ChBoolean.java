package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.Check.CheckDigital;

/**
 * Creates a check that will always return a set value.
 * True will mean that the action immediately ends.
 * False will mean that the action will only end when another action is queued.
 * 
 * To have an action never end, use ChNever.
 * 
 * @author Sean Zammit
 */
public class ChBoolean extends CheckDigital
{
	/**
	 * @param checkResult The value that the check will always return.
	 */
	public ChBoolean(boolean checkResult) {
		super(checkResult);
	}

	public boolean isDone() {
		return type;
	}
}
