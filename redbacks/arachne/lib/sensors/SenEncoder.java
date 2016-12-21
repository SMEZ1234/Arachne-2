package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.Encoder;

/**
 * A generic encoder attached to digital ports on the RIO.
 *
 * @author Sean Zammit
 */
public class SenEncoder extends NumericSensor
{
	Encoder sensor;

	/**
	 * Constructor for a generic encoder attached to digital ports on the RIO.
	 * 
	 * @param sensor A WPILib instance of the encoder, which should extend {@link Encoder}.
	 */
	public SenEncoder(Encoder sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.get();
	}
}
