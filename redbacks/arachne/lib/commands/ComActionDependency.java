package redbacks.arachne.lib.commands;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.digital.ChAction;

/**
 * A command used by Arachne to set subsystem requirements for individual actions.
 * See {@link Action#systemDependencies Action.systemDependencies}.
 *
 * @author Sean Zammit
 */
public class ComActionDependency extends CommandBase
{
	Action parentAction;
	
	/**
	 * Constructor for a command that occupies a subsystem in order to create subsystem requirements for individual actions.
	 * See {@link Action#systemDependencies Action.systemDependencies}.
	 * 
	 * @param requiredSystem The subsystem on which this command will be run.
	 * @param parentAction The action with a subsystem requirement.
	 */
	public ComActionDependency(SubsystemBase requiredSystem, Action parentAction) {
		super(requiredSystem, new AcDoNothing(new ChAction(parentAction)));
		this.parentAction = parentAction;
	}
	
	public void interrupted() {
		super.interrupted();
		parentAction.cancel();
	}
}
