package redbacks.arachne.lib.checks.can;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.sensors.CANAnalog;

/**
 * Checks whether an analog sensor wired into a CAN controller has reached a specific value.
 * 
 * @author Sean Zammit
 */
public class ChCANAnalog extends CheckAnalog
{
	CANAnalog sensor;
	
	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 */
	public ChCANAnalog(double value, CANAnalog sensor) {
		this(value, sensor, true);
	}
	
	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 */
	public ChCANAnalog(double value, CANAnalog sensor, boolean isGreaterThan) {
		this(value, sensor, isGreaterThan, true);
	}

	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 */
	public ChCANAnalog(double value, CANAnalog sensor, boolean isGreaterThan, boolean useAbsoluteReading) {
		this(value, sensor, isGreaterThan, useAbsoluteReading, true);
	}

	/**
	 * @param value The analog value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 * @param shouldReset Whether whatever is returning an analog reading should be reset to 0 at the beginning of the check.
	 */
	public ChCANAnalog(double value, CANAnalog sensor, boolean isGreaterThan, boolean useAbsoluteReading, boolean shouldReset) {
		super(value, isGreaterThan, useAbsoluteReading, shouldReset);
		this.sensor = sensor;
	}
	
	public double getAnalogValue() {
		return sensor.get();
	}
	
	public void onStart() {
		if(this.shouldReset) sensor.reset();
	}
}
