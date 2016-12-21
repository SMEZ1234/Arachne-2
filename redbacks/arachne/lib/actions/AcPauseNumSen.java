package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * An action to 'pause' the readings from a {@link NumericSensor NumericSensor}.
 * All readings returned while paused will be the same as the value it was paused at, and once unpaused the sensor will continue to function from the same reading, regardless of whether its value has changed.
 * 
 * @author Sean Zammit
 */
public class AcPauseNumSen extends Action
{
	public NumericSensor sensor;
	public boolean pause;

	/**
	 * Constructor for an action that will set the pause state of a {@link NumericSensor NumericSensor}.
	 * 
	 * @param sensor The sensor to set the pause state of.
	 * @param pause The desired pause state. True is paused, false is unpaused.
	 */
	public AcPauseNumSen(NumericSensor sensor, boolean pause) {
		super(new ChTrue());
		this.sensor = sensor;
		this.pause = pause;
	}

	public void onFinish() {
		if(pause) sensor.pause();
		else sensor.unpause();
	}
}
