package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.digital.ChBoolean;
import redbacks.arachne.lib.sensors.NumericSensor;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcSetNumSen extends Action
{
	public NumericSensor sensor;
	public int value;
	
	public AcSetNumSen(NumericSensor sensor, int value) {
		super(new ChBoolean(true));
		this.sensor = sensor;
		this.value = value;
	}

	public void onFinish() {
		sensor.set(value);
	}
}
