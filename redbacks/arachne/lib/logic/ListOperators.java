package redbacks.arachne.lib.logic;

import java.util.ArrayList;

public enum ListOperators implements ListLogic {
	ONCE,
	ORDER;
	
	public ArrayList<GettableBoolean> workingList = new ArrayList<GettableBoolean>();

	public boolean get() {
		switch(this) {
			case ONCE:
				for(int i = workingList.size() - 1; i >= 0; i--) if(workingList.get(i).get()) workingList.remove(i);
				return workingList.size() == 0;
			case ORDER:
				while(workingList.size() > 0 && workingList.get(0).get()) workingList.remove(0);
				return workingList.size() == 0;
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
