package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * A superclass for all sensors returning a boolean reading.
 *
 * @author Sean Zammit
 */
public abstract class BinarySensor implements GettableBoolean
{
	/** Whether the sensor reading should be inverted. */
	public boolean isInverted;

	public final boolean get() {
		return getSenVal() != isInverted;
	}

	/**
	 * Overridden by individual sensor classes to specify their reading.
	 * 
	 * @return The sensor value to be used by BinarySensor
	 */
	protected abstract boolean getSenVal();

	/**
	 * Inverts the reading from the sensor.
	 * 
	 * @param shouldBeInverted Whether the sensor reading should be inverted.
	 */
	public final void setInverted(boolean shouldBeInverted) {
		isInverted = shouldBeInverted;
	}
}
