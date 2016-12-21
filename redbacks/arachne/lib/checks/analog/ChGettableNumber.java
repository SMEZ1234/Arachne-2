package redbacks.arachne.lib.checks.analog;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.logic.GettableNumber;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * Checks whether the value of any {@link GettableNumber GettableNumber} has passed a specific point.
 * Only use this if the gettable is not also a valid {@link NumericSensor NumericSensor}.
 * 
 * @author Sean Zammit
 */
public class ChGettableNumber extends CheckAnalog
{
	GettableNumber gettable;

	/**
	 * Constructor for a check that returns true once the value of a {@link GettableNumber GettableNumber} is greater than a specific value.
	 * 
	 * @param value The target value.
	 * @param gettable The gettable being checked.
	 */
	public ChGettableNumber(double value, GettableNumber gettable) {
		this(value, gettable, true);
	}

	/**
	 * Constructor for a check that returns true once the value of a {@link GettableNumber GettableNumber} is on a certain side of a specific value.
	 * 
	 * @param value The target value.
	 * @param gettable The gettable being checked.
	 * @param isGreaterThan Whether the reading is required to be greater than the target value.
	 */
	public ChGettableNumber(double value, GettableNumber gettable, boolean isGreaterThan) {
		this(value, gettable, isGreaterThan, true);
	}

	/**
	 * Constructor for a check that returns true once the value of a {@link GettableNumber GettableNumber} is on a certain side of a specific value.
	 * It also allows you to specify whether the reading should be treated as an absolute value.
	 * 
	 * @param value The target value.
	 * @param gettable The gettable being checked.
	 * @param isGreaterThan Whether the reading is required to be greater than the target value.
	 * @param useAbsoluteReading Whether the absolute reading should be used.
	 */
	public ChGettableNumber(double value, GettableNumber gettable, boolean isGreaterThan, boolean useAbsoluteReading) {
		super(value, isGreaterThan, useAbsoluteReading, false);
		this.gettable = gettable;
	}

	public double getAnalogValue() {
		return gettable.get();
	}
}
