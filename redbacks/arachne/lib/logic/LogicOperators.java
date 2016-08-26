package redbacks.arachne.lib.logic;

import java.util.ArrayList;

public enum LogicOperators implements ListLogic {
	AND,
	OR,
	NAND,
	NOR,
	
	XOR,
	XNOR;
	
	public ArrayList<GettableBoolean> workingList = new ArrayList<GettableBoolean>();

	public boolean get() {
		int count = 0;
		switch(this) {
			case AND:
				for(GettableBoolean gettable : workingList) if(!gettable.get()) return false;
				return true;
			case OR:
				for(GettableBoolean gettable : workingList) if(gettable.get()) return true;
				return false;
			case NAND:
				for(GettableBoolean gettable : workingList) if(!gettable.get()) return true;
				return false;
			case NOR:
				for(GettableBoolean gettable : workingList) if(gettable.get()) return false;
				return true;
			case XOR:
				for(GettableBoolean gettable : workingList) if(gettable.get()) count++;
				return count != 0 && count != workingList.size();
			case XNOR:
				for(GettableBoolean gettable : workingList) if(gettable.get()) count++;
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
		for(GettableBoolean gettable : list) workingList.add(gettable);
	}
}
