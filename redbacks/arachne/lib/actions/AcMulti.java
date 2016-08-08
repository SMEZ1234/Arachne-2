package redbacks.arachne.lib.actions;

import java.util.ArrayList;

import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.checks.ChNever;
import redbacks.arachne.lib.checks.digital.ChBoolean;

/**
 * This is an action that enables multiple actions to be run in parallel.
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
	 * @param check The condition that will end this command regardless of the state of the individual actions it holds.
	 * The command will also finish when all of its individual actions have finished.
	 * @param actions The actions that will be run by this command.
	 */
	public AcMulti(Check check, Action... actions) {
		super(check);
		for(Action action : actions) {
			this.actions.add(action);
			this.originalList.add(action);
		}
	}
	
	/**
	 * Alternate constructor assuming ChNever for the list of Actions.
	 * The command will finish when all of its individual actions have finished.
	 * 
	 * @param actions The actions that will be run by this command.
	 */
	public AcMulti(Action... actions) {
		this(new ChNever(), actions);
	}
	
	public void onRun() {
		for(int i = actions.size() - 1; i >= 0; i--) {
			if(actions.get(i).isFinished()) {
				actions.get(i).end();
				actions.remove(i);
			}
			else actions.get(i).periodic();
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
		boolean areAllBooleans = true;
		for(Action action : actions) if(!(action.check instanceof ChBoolean)) areAllBooleans = false;
		return actions.size() <= 0 || areAllBooleans;
	}
}
