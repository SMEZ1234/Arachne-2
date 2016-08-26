package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.logic.GettableBoolean;
import redbacks.arachne.lib.logic.ListLogic;

/**
 * Holds all the checks that have multiple conditions.
 * Also functions as their superclass.
 * 
 * @author Sean Zammit
 */
public class ChGettableBooleanList extends Check
{
	/** The list of subchecks for the main check. */
	public GettableBoolean[] conditionList;

	ListLogic operator;
	
	/**
	 * @param checks The list of subchecks for the main check.
	 */
	public ChGettableBooleanList(ListLogic operator, GettableBoolean... conditions) {
		this.operator = operator;
		if(conditions.length == 0) conditionList = new Check[] {new ChTrue()};
		else conditionList = conditions;
		this.operator.populateWorkingList(conditionList);
	}
	
	public void onStart() {
		operator.populateWorkingList(conditionList);
	}
	
	protected boolean isDone() {
		return operator.get();
	}
}
