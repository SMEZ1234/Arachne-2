package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChQueue;
import redbacks.arachne.lib.checks.Check;

/**
 * This action should be called when the command should not perform any function.
 * It functions as a placeholder so that the command does not end.
 * 
 * @author Sean Zammit
 */
public class AcDoNothing extends Action
{
	public AcDoNothing() {
		this(new ChQueue());
	}
	
	public AcDoNothing(Check check) {
		super(check);
	}
}
