package redbacks.arachne.lib.checks.analog;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * Checks whether the value of any {@link NumericSensor NumericSensor} has passed a specific point.
 * 
 * @author Sean Zammit
 */
public class ChNumSen extends CheckAnalog
{
	NumericSensor sensor;

	/**
	 * Constructor for a check that returns true once the value of a {@link NumericSensor NumericSensor} is greater than a specific value.
	 * 
	 * @param value The target value.
	 * @param sensor The sensor being checked.
	 */
	public ChNumSen(double value, NumericSensor sensor) {
		this(value, sensor, true);
	}

	/**
	 * Constructor for a check that returns true once the value of a {@link NumericSensor NumericSensor} is on a certain side of a specific value.
	 * 
	 * @param value The target value.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the reading is required to be greater than the target value.
	 */
	public ChNumSen(double value, NumericSensor sensor, boolean isGreaterThan) {
		this(value, sensor, isGreaterThan, true);
	}

	/**
	 * Constructor for a check that returns true once the value of a {@link NumericSensor NumericSensor} is on a certain side of a specific value.
	 * It also allows you to specify whether the reading should be treated as an absolute value.
	 * 
	 * @param value The target value.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the reading is required to be greater than the target value.
	 * @param useAbsoluteReading Whether the absolute reading should be used.
	 */
	public ChNumSen(double value, NumericSensor sensor, boolean isGreaterThan, boolean useAbsoluteReading) {
		this(value, sensor, isGreaterThan, useAbsoluteReading, true);
	}

	/**
	 * Constructor for a check that returns true once the value of a {@link NumericSensor NumericSensor} is on a certain side of a specific value.
	 * It also allows you to specify whether the reading should be treated as an absolute value, and whether the sensor should be reset when the check is initialised.
	 * 
	 * @param value The target value.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the reading is required to be greater than the target value.
	 * @param useAbsoluteReading Whether the absolute reading should be used.
	 * @param shouldReset Whether the sensor readings should be reset to 0 when the check is initialised.
	 */
	public ChNumSen(double value, NumericSensor sensor, boolean isGreaterThan, boolean useAbsoluteReading, boolean shouldReset) {
		super(value, isGreaterThan, useAbsoluteReading, shouldReset);
		this.sensor = sensor;
	}

	public double getAnalogValue() {
		return sensor.get();
	}

	public void onStart() {
		if(this.shouldReset) sensor.set(0);
	}
}
