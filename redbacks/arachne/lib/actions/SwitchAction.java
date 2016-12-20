package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * An action that will run one action from a list of actions based on conditions.
 *
 * @author Sean Zammit
 */
public class SwitchAction extends Action
{
	public Action chosenAction;
	
	private Action defaultAction;
	private OptionAction[] options;
	
	/**
	 * Constructor for an action that will run one action from a list of options.
	 * 
	 * @param defaultAction The action to run if no conditions are met.
	 * @param options Pairings of actions and conditions, which will be checked until one of the conditions is met, or the list ends.
	 */
	public SwitchAction(Action defaultAction, OptionAction... options) {
		super(new ChFalse());
		this.defaultAction = defaultAction;
		this.options = options;
	}
	
	/**
	 * A pairing of an action with the condition required for it to be run. Used only in {@link SwitchAction SwitchAction}.
	 *
	 * @author Sean Zammit
	 */
	public static class OptionAction
	{
		public GettableBoolean requirement;
		public Action option;
		
		/**
		 * Constructor to pair an action with a required condition.
		 * Note that a {@link Check Check} is a valid {@link GettableBoolean GettableBoolean}, but its {@link Check#initialise(redbacks.arachne.lib.commands.CommandBase, Action) initialise(CommandBase, Action)}, {@link Check#onRun() onRun()} and {@link Check#onFinish() onFinish()} methods will not be called.
		 * 
		 * @param requirement The boolean value determining whether the action should be run.
		 * @param option The action to run.
		 */
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
