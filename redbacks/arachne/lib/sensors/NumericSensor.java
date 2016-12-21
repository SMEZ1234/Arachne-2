package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableNumber;

/**
 * A superclass for all sensors returning a numeric reading.
 *
 * @author Sean Zammit
 */
public abstract class NumericSensor implements GettableNumber
{
	double offset = 0, pauseValue = 0;
	boolean isPaused = false;

	public final double get() {
		if(isPaused) return pauseValue;
		return getSenVal() + offset;
	}

	/**
	 * Overridden by individual sensor classes to specify their reading.
	 * 
	 * @return The sensor value to be used by NumericSensor.
	 */
	protected abstract double getSenVal();

	/**
	 * Sets the value of the sensor.
	 * Any change in reading will then be from this point.
	 * 
	 * @param value The value to set the sensor to.
	 */
	public final void set(double value) {
		offset = value - getSenVal();
	}

	/**
	 * Pauses the reading from the sensor.
	 * The reading will remain constant while paused.
	 */
	public final void pause() {
		isPaused = true;
		pauseValue = getSenVal() + offset;
	}

	/**
	 * Unpauses the reading from the sensor.
	 * Any change in reading will then be from the paused value.
	 */
	public final void unpause() {
		isPaused = false;
		set(pauseValue);
	}
}
