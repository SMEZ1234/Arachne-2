package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A generic digital sensor attached to a digital port on the RIO.
 *
 * @author Sean Zammit
 */
public class SenDI extends BinarySensor
{
	DigitalInput sensor;

	/**
	 * Constructor for a generic digital sensor attached to a digital port on the RIO.
	 * 
	 * @param sensor A WPILib instance of the sensor, which should extend {@link DigitalInput}.
	 */
	public SenDI(DigitalInput sensor) {
		this.sensor = sensor;
	}

	protected boolean getSenVal() {
		return sensor.get();
	}
}
