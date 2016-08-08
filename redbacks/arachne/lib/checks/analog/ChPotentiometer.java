package redbacks.arachne.lib.checks.analog;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import redbacks.arachne.lib.checks.Check.CheckAnalog;

/**
 * Checks whether the value of a potentiometer has passed a specific point.
 * 
 * @author Sean Zammit
 */
public class ChPotentiometer extends CheckAnalog
{
	AnalogPotentiometer sensor;
	
	/**
	 * @param value The potentiometer value being checked for.
	 * @param sensor The sensor being checked.
	 */
	public ChPotentiometer(double value, AnalogPotentiometer sensor) {
		this(value, sensor, true);
	}
	
	/**
	 * @param value The potentiometer value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 */
	public ChPotentiometer(double value, AnalogPotentiometer sensor, boolean isGreaterThan) {
		this(value, sensor, isGreaterThan, true);
	}

	/**
	 * @param value The potentiometer value being checked for.
	 * @param sensor The sensor being checked.
	 * @param isGreaterThan Whether the required reading is greater than the input.
	 * @param useAbsoluteReading Whether the absolute analog reading should be used.
	 */
	public ChPotentiometer(double value, AnalogPotentiometer sensor, boolean isGreaterThan, boolean useAbsoluteReading) {
		super(value, isGreaterThan, useAbsoluteReading, false);
		this.sensor = sensor;
	}
	
	public double getAnalogValue() {
		return sensor.get();
	}
}
