package redbacks.arachne.lib.commands;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.digital.ChAction;

public class ComActionDependency extends CommandBase
{
	Action parentAction;
	
	public ComActionDependency(SubsystemBase requiredSystem, Action parentAction) {
		super(requiredSystem, new AcDoNothing(new ChAction(parentAction)));
		this.parentAction = parentAction;
	}
	
	public void interrupted() {
		super.interrupted();
		parentAction.cancel();
	}
}
