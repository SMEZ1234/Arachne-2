package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * A generic analog sensor attached to an analog channel on the RIO.
 *
 * @author Sean Zammit
 */
public class SenAnalog extends NumericSensor
{
	AnalogInput sensor;

	/**
	 * Constructor for a generic analog sensor attached to an analog channel on the RIO.
	 * 
	 * @param sensor A WPILib instance of the sensor, which should extend {@link AnalogInput}.
	 */
	public SenAnalog(AnalogInput sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.getVoltage();
	}
}
