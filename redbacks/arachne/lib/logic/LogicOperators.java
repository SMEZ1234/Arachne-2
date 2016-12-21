package redbacks.arachne.lib.logic;

import java.util.ArrayList;

/**
 * Contains all {@link ListLogic} operators that are not specific to lists - currently all are binary operators.
 *
 * @author Sean Zammit
 */
public enum LogicOperators implements ListLogic
{
	/** An operator that requires that all conditions are true. */
	AND,

	/** An operator that requires that any condition is true. */
	OR,

	/** An operator that requires that any condition is false. */
	NAND,

	/** An operator that requires that all conditions are false. */
	NOR,

	/** An operator that requires that some but not all conditions are true. */
	XOR,

	/** An operator that requires that all or no conditions are true. */
	XNOR;

	/**
	 * The list of conditions that will be evaluated based on the operator.
	 */
	public ArrayList<GettableBoolean> workingList = new ArrayList<GettableBoolean>();

	public boolean get() {
		int count = 0;
		switch(this) {
			case AND:
				for(GettableBoolean gettable : workingList)
					if(!gettable.get()) return false;
				return true;
			case OR:
				for(GettableBoolean gettable : workingList)
					if(gettable.get()) return true;
				return false;
			case NAND:
				for(GettableBoolean gettable : workingList)
					if(!gettable.get()) return true;
				return false;
			case NOR:
				for(GettableBoolean gettable : workingList)
					if(gettable.get()) return false;
				return true;
			case XOR:
				for(GettableBoolean gettable : workingList)
					if(gettable.get()) count++;
				return count != 0 && count != workingList.size();
			case XNOR:
				for(GettableBoolean gettable : workingList)
					if(gettable.get()) count++;
				return count == 0 || count == workingList.size();
			default:
				return false;
		}
	}

	public void populateWorkingList(ArrayList<GettableBoolean> list) {
		workingList = list;
	}

	public void populateWorkingList(GettableBoolean... list) {
		workingList.clear();
		for(GettableBoolean gettable : list)
			workingList.add(gettable);
	}
}
