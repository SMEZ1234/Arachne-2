package redbacks.arachne.lib.checks.digital;

import redbacks.arachne.lib.checks.Check.CheckDigital;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * Checks the value of any {@link GettableBoolean GettableBoolean}.
 * 
 * @author Sean Zammit
 */
public class ChGettableBoolean extends CheckDigital
{
	GettableBoolean gettable;

	/**
	 * @param gettable The gettable being checked.
	 * @param isTrue Whether the required value is true.
	 */
	public ChGettableBoolean(GettableBoolean gettable, boolean isTrue) {
		super(isTrue);
		this.gettable = gettable;
	}

	public boolean isDone() {
		return gettable.get() == type;
	}
}