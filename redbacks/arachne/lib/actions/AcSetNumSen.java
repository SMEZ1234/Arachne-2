package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * An action to set the reading from a {@link NumericSensor NumericSensor} to a certain value.
 * All future readings will then be from this new starting point.
 * 
 * @author Sean Zammit
 */
public class AcSetNumSen extends Action
{
	public NumericSensor sensor;
	public double value;
	
	/**
	 * Constructor for an action which will set the value returned by a {@link NumericSensor NumericSensor}.
	 * 
	 * @param sensor The sensor to set.
	 * @param value The numeric value to set it to.
	 */
	public AcSetNumSen(NumericSensor sensor, double value) {
		super(new ChTrue());
		this.sensor = sensor;
		this.value = value;
	}

	public void onFinish() {
		sensor.set(value);
	}
}
