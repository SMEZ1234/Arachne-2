package redbacks.arachne.lib.logic;

import java.util.ArrayList;

/**
 * An interface for use in determining how a list of conditions is used.
 *
 * @author Sean Zammit
 */
public interface ListLogic
{
	/**
	 * Determines whether the list of conditions is fulfilled, depending on the operator.
	 * 
	 * @return Whether the condition is fulfilled.
	 */
	public boolean get();

	/**
	 * Creates a list of conditions, from which the outcome will be evaluated.
	 * 
	 * @param list The list of conditions.
	 */
	public void populateWorkingList(ArrayList<GettableBoolean> list);

	/**
	 * Creates a list of conditions, from which the outcome will be evaluated.
	 * 
	 * @param list The list of conditions.
	 */
	public void populateWorkingList(GettableBoolean... list);
}
