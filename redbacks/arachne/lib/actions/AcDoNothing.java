package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.checks.digital.ChBoolean;

/**
 * This action should be called when the command should not perform any function.
 * It functions as a placeholder so that the command does not end.
 * 
 * @author Sean Zammit
 */
public class AcDoNothing extends Action
{
	public AcDoNothing() {
		this(new ChBoolean(false));
	}
	
	public AcDoNothing(Check check) {
		super(check);
	}
}
