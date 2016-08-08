package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.checks.digital.ChBoolean;
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
		if(checks.length == 0) checklist = new Check[] {new ChBoolean(true)};
		else checklist = checks;
		this.operator.populateWorkingList(checklist);
	}
	
	public void onStart() {
		operator.populateWorkingList(checklist);
	}

	protected boolean isDone() {
		return operator.get();
	}
}
