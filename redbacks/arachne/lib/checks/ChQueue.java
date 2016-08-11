package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.checks.Check.CheckDigital;

/**
 * Creates a check that will always return a set value.
 * 
 * To have an action never end, use ChFalse.
 * 
 * @author Sean Zammit
 */
public class ChQueue extends CheckDigital
{
	/**
	 * @param checkResult The value that the check will always return.
	 */
	public ChQueue() {
		super(false);
	}

	public boolean isDone() {
		return type;
	}
}
