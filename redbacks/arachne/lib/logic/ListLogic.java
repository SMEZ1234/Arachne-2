package redbacks.arachne.lib.logic;

import java.util.ArrayList;

public interface ListLogic
{
	public boolean get();

	public void populateWorkingList(ArrayList<GettableBoolean> list);
	public void populateWorkingList(GettableBoolean... list);
}
