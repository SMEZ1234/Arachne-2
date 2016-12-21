package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.ChMulti;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.logic.GettableBoolean;
import redbacks.arachne.lib.logic.ListLogic;

/**
 * A check with multiple {@link GettableBoolean GettableBooleans} as conditions.
 * These conditions can be selectively used through a {@link ListLogic ListLogic} operator.
 * For a check using multiple checks as conditions, see {@link ChMulti ChMulti}.
 * 
 * @author Sean Zammit
 */
public class ChGettableBooleanList extends Check
{
	/** The list of gettables for the check. */
	public GettableBoolean[] conditionList;

	ListLogic operator;

	/**
	 * Constructor for a check with multiple {@link GettableBoolean GettableBooleans} as conditions.
	 * 
	 * @param operator The {@link ListLogic ListLogic} operator that determines the use of the list of conditions.
	 * @param conditions The list of gettables for the check.
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
