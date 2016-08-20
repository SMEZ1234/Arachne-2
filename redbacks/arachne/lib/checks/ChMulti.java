package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.logic.ListLogic;

/**
 * Holds all the checks that have multiple conditions.
 * Also functions as their superclass.
 * 
 * @author Sean Zammit
 */
public class ChMulti extends Check
{
	/** The list of subchecks for the main check. */
	public Check[] checklist;

	ListLogic operator;
	
	/**
	 * @param checks The list of subchecks for the main check.
	 */
	public ChMulti(ListLogic operator, Check... checks) {
		this.operator = operator;
		if(checks.length == 0) checklist = new Check[] {new ChTrue()};
		else checklist = checks;
		this.operator.populateWorkingList(checklist);
	}
	
	public void onStart() {
		operator.populateWorkingList(checklist);
	}
	
	public void initialise(CommandBase command, Action action) {
		super.initialise(command, action);
		for(Check check : checklist) check.initialise(command, action);
	}
	
	public void onRun() {
		for(Check check : checklist) check.onRun();
	}
	
	public void onFinish() {
		for(Check check : checklist) check.onFinish();
	}

	protected boolean isDone() {
		return operator.get();
	}
}
