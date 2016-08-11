package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableNumber;

public abstract class NumericSensor implements GettableNumber
{
	double offset = 0, pauseValue = 0;
	boolean isPaused = false;
	
	public final double get() {
		if(isPaused) return pauseValue;
		return getSenVal() + offset;
	}
	
	/**
	 * DO NOT CALL THIS.
	 * It should only be accessed by NumericSensor. Use get() instead.
	 * 
	 * @return The sensor value to be used by NumericSensor
	 */
	protected abstract double getSenVal();
	
	public final void set(double value) {
		offset = value - getSenVal();
	}
	
	public final void pause() {
		isPaused = true;
		pauseValue = getSenVal();
	}
	
	public final void unpause() {
		isPaused = false;
		set(pauseValue);
	}
}
