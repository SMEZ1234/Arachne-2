package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.logic.ListLogic;

/**
 * A check with multiple subchecks as conditions.
 * These conditions can be selectively used through a {@link ListLogic ListLogic} operator.
 * 
 * @author Sean Zammit
 */
public class ChMulti extends Check
{
	/** The list of subchecks for the multi-check. */
	public Check[] checklist;

	ListLogic operator;

	/**
	 * Constructor for a check with multiple subchecks as conditions.
	 * 
	 * @param operator The {@link ListLogic ListLogic} operator that determines the use of the list of conditions.
	 * @param checks The list of subchecks for the multi-check.
	 */
	public ChMulti(ListLogic operator, Check... checks) {
		this.operator = operator;
		if(checks.length == 0) checklist = new Check[] {new ChTrue()};
		else checklist = checks;
		this.operator.populateWorkingList(checklist);
	}

	public void onStart() {
		for(Check check : checklist) check.initialise(command, action);
		operator.populateWorkingList(checklist);
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
