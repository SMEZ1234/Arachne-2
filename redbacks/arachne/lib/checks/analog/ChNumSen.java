package redbacks.arachne.lib.checks.analog;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * Checks whether an analog sensor wired into a CAN controller has reached a specific value.
 * 
 * @author Sean Zammit
 */
public class ChNumSen extends CheckAnalog
{
	NumericSensor sensor;
	
	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 */
	public ChNumSen(double value, NumericSensor sensor) {
		this(value, sensor, true);
	}
	
	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 */
	public ChNumSen(double value, NumericSensor sensor, boolean isGreaterThan) {
		this(value, sensor, isGreaterThan, true);
	}

	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 */
	public ChNumSen(double value, NumericSensor sensor, boolean isGreaterThan, boolean useAbsoluteReading) {
		this(value, sensor, isGreaterThan, useAbsoluteReading, true);
	}

	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 * @param shouldReset Whether whatever is returning an analog reading should be reset to 0 at the beginning of the check.
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
