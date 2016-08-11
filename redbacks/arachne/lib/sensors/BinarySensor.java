package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableBoolean;

public abstract class BinarySensor implements GettableBoolean
{
	public boolean isInverted;
	
	public final boolean get() {
		return getSenVal() != isInverted;
	}
	
	/**
	 * DO NOT CALL THIS.
	 * It should only be accessed by BinarySensor. Use get() instead.
	 * 
	 * @return The sensor value to be used by BinarySensor
	 */
	protected abstract boolean getSenVal();
	
	public final void setInverted(boolean shouldBeInverted) {
		isInverted = shouldBeInverted;
	}
}
