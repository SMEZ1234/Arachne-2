package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.Check.CheckDigital;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * Checks the value of any gettable boolean.
 * Only use this if there is no other check for the gettable - there might be additional functions in those checks.
 * 
 * @author Sean Zammit
 */
public class ChGettableBoolean extends CheckDigital {
	
	GettableBoolean gettable;
	
	/**
	 * @param gettable The gettable being checked.
	 * @param isTriggered Whether the value should be true.
	 */
	public ChGettableBoolean(GettableBoolean gettable, boolean isTriggered) {
		super(isTriggered);
		this.gettable = gettable;
	}

	public boolean isDone() {
		return gettable.get();
	}
}