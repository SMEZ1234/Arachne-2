package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.logic.GettableBoolean;

public class SwitchCheck extends Check
{
	public Check chosenCheck;
	
	private Check defaultCheck;
	private OptionCheck[] options;
	
	public SwitchCheck(Check defaultCheck, OptionCheck... options) {
		this.defaultCheck = defaultCheck;
		this.options = options;
	}
	
	public static class OptionCheck {
		public GettableBoolean requirement;
		public Check option;
		
		public OptionCheck(GettableBoolean requirement, Check option) {
			this.requirement = requirement;
			this.option = option;
		}
	}
	
	public void onStart() {
		for(OptionCheck option : options) if(option.requirement.get()) {
			chosenCheck = option.option;
			chosenCheck.initialise(command, action);
			return;
		}
		chosenCheck = defaultCheck;
		chosenCheck.initialise(command, action);
	}
	
	public void onRun() {
		chosenCheck.onRun();
	}
	
	public void onFinish() {
		chosenCheck.onFinish();
	}

	protected boolean isDone() {
		return chosenCheck.isFinished();
	}
}
