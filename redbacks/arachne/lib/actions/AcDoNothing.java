package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChQueue;
import redbacks.arachne.lib.checks.Check;

/**
 * This action should be called when the command should not perform any function.
 * It can function as a placeholder so that the command does not end, a break until a check's condition is met, or a convenient blank command to be run by default on a subsystem.
 * 
 * @author Sean Zammit
 */
public class AcDoNothing extends Action
{
	/**
	 * Constructor for an action that ends once another action is queued. See {@link ChQueue ChQueue}.
	 */
	public AcDoNothing() {
		this(new ChQueue());
	}
	
	/**
	 * Constructor for a blank action that ends once a condition is met.
	 * 
	 * @param check The condition to be met.
	 */
	public AcDoNothing(Check check) {
		super(check);
	}
}
