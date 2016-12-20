package redbacks.arachne.lib.actions;

import java.util.ArrayList;

import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.ChQueue;

/**
 * An action that enables multiple actions to be run at the same time before progressing the sequence.
 * 
 * @author Sean Zammit
 */
public class AcMulti extends Action
{
	/** The list of actions that will be run by this command. */
	ArrayList<Action> actions = new ArrayList<Action>();
	
	/** The original list of actions, used to reset the list. */
	ArrayList<Action> originalList = new ArrayList<Action>();
	
	/**
	 * Constructor to add an alternate ending condition to the multi-action.
	 * 
	 * @param check The condition that will end this multi-action regardless of the state of the individual actions it holds.
	 * The action will also finish when all of its individual actions have finished.
	 * @param actions The actions that will be run by this multi-action.
	 */
	public AcMulti(Check check, Action... actions) {
		super(check);
		for(Action action : actions) {
			this.actions.add(action);
			this.originalList.add(action);
		}
	}
	
	/**
	 * Alternate constructor assuming {@link ChFalse ChFalse} for the list of Actions.
	 * The multi-action will finish when all of its individual actions have finished.
	 * 
	 * @param actions The actions that will be run by this multi-action.
	 */
	public AcMulti(Action... actions) {
		this(new ChFalse(), actions);
	}
	
	public void onRun() {
		for(int i = actions.size() - 1; i >= 0; i--) {
			if(actions.get(i).isFinished()) {
				actions.get(i).end();
				actions.remove(i);
			}
			else actions.get(i).execute();
		}
	}	
	
	public void onStart() {
		for(Action action : actions) action.initialise(command);
	}
	
	public void onFinish() {
		for(Action action : actions) action.end();
		actions.clear();
		for(Action action : originalList) actions.add(action);
	}

	public boolean isDone() {
		boolean areAllQueues = true;
		for(Action action : actions) if(!(action.check instanceof ChQueue)) areAllQueues = false;
		return actions.size() == 0 || areAllQueues;
	}
}
