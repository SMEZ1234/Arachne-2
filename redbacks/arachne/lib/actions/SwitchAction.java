package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.logic.GettableBoolean;

public class SwitchAction extends Action
{
	public Action chosenAction;
	
	private Action defaultAction;
	private OptionAction[] options;
	
	public SwitchAction(Action defaultAction, OptionAction... options) {
		super(new ChFalse());
		this.defaultAction = defaultAction;
		this.options = options;
	}
	
	public static class OptionAction {
		public GettableBoolean requirement;
		public Action option;
		
		public OptionAction(GettableBoolean requirement, Action option) {
			this.requirement = requirement;
			this.option = option;
		}
	}
	
	public void onStart() {
		for(OptionAction option : options) if(option.requirement.get()) {
			chosenAction = option.option;
			chosenAction.initialise(command);
			return;
		}
		chosenAction = defaultAction;
		chosenAction.initialise(command);
	}
	
	public void onRun() {
		chosenAction.execute();
	}
	
	public void onFinish() {
		chosenAction.end();
	}

	protected boolean isDone() {
		return chosenAction.isFinished();
	}
}
