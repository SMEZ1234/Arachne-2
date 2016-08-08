package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTime;

/**
 * A convenient shortcut action that does nothing for a specified amount of time before starting the next command.
 * 
 * @author Sean Zammit
 */
public class AcWait extends Action
{
	/**
	 * @param timeout The number of seconds (as a decimal) that the action will wait for.
	 */	
	public AcWait(double timeout) {
		super(new ChTime(timeout));
	}
}
