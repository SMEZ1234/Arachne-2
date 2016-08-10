package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableNumber;

public class NumericSensor implements GettableNumber
{
	double offset = 0, pauseValue = 0;
	boolean isPaused = false;
	GettableNumber sensor;	
	
	public NumericSensor(GettableNumber sensor) {
		this.sensor = sensor;
	}

	public double get() {
		if(isPaused) return pauseValue;
		return sensor.get() + offset;
	}
	
	public void set(double value) {
		offset = value - sensor.get();
	}
	
	public void pause() {
		isPaused = true;
		pauseValue = sensor.get();
	}
	
	public void unpause() {
		isPaused = false;
		set(pauseValue);
	}
}
