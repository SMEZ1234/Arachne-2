package redbacks.arachne.lib.commands;

import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.digital.ChAction;
import redbacks.arachne.lib.subsystems.SubsystemBase;

public class ComActionDependency extends CommandBase
{
	Action parentAction;
	
	public ComActionDependency(SubsystemBase requiredSystem, Action parentAction) {
		super(requiredSystem, new AcDoNothing(new ChAction(parentAction)));
		this.parentAction = parentAction;
	}
	
	public void interrupted() {
		parentAction.cancel();
	}
}