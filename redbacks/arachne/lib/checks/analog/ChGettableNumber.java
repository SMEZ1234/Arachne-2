package redbacks.arachne.lib.checks.analog;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.logic.GettableNumber;

/**
 * Checks whether the value of any gettable number has passed a specific point.
 * 
 * @author Sean Zammit
 */
public class ChGettableNumber extends CheckAnalog
{
	GettableNumber gettable;
	
	/**
	 * @param value The potentiometer value being checked for.
	 * @param gettable The gettable being checked.
	 */
	public ChGettableNumber(double value, GettableNumber gettable) {
		this(value, gettable, true);
	}
	
	/**
	 * @param value The potentiometer value being checked for.
	 * @param gettable The gettable being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 */
	public ChGettableNumber(double value, GettableNumber gettable, boolean isGreaterThan) {
		this(value, gettable, isGreaterThan, true);
	}

	/**
	 * @param value The potentiometer value being checked for.
	 * @param gettable The gettable being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 */
	public ChGettableNumber(double value, GettableNumber gettable, boolean isGreaterThan, boolean useAbsoluteReading) {
		super(value, isGreaterThan, useAbsoluteReading, false);
		this.gettable = gettable;
	}
	
	public double getAnalogValue() {
		return gettable.get();
	}
}
