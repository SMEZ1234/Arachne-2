package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * A check that will run one check from a list of checks based on conditions.
 *
 * @author Sean Zammit
 */
public class SwitchCheck extends Check
{
	/** The check that has been selected to run. Will be null until {@link #onStart() onStart()} is called. */
	public Check chosenCheck;
	
	private Check defaultCheck;
	private OptionCheck[] options;
	
	/**
	 * Constructor for a check that will run one check from a list of options.
	 * 
	 * @param defaultCheck The check to run if no conditions are met.
	 * @param options Pairings of checks and conditions, which will be checked until one of the conditions is met, or the list ends.
	 */
	public SwitchCheck(Check defaultCheck, OptionCheck... options) {
		this.defaultCheck = defaultCheck;
		this.options = options;
	}
	
	/**
	 * A pairing of a check with the condition required for it to be run. Used only in {@link SwitchCheck SwitchCheck}.
	 *
	 * @author Sean Zammit
	 */
	public static class OptionCheck
	{
		/** The boolean value determining whether the check should be run. */
		public GettableBoolean requirement;
		
		/** The check to run if the condition is met. */
		public Check option;
		
		/**
		 * Constructor to pair a check with a required condition.
		 * Note that another {@link Check Check} is a valid {@link GettableBoolean GettableBoolean}, but its {@link Check#initialise(redbacks.arachne.lib.commands.CommandBase, redbacks.arachne.lib.actions.Action) initialise(CommandBase, Action)}, {@link Check#onRun() onRun()} and {@link Check#onFinish() onFinish()} methods will not be called.
		 * 
		 * @param requirement The boolean value determining whether the check should be run.
		 * @param option The check to run.
		 */
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
