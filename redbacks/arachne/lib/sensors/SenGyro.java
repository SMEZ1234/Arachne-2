package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * A generic gyroscopic sensor.
 *
 * @author Sean Zammit
 */
public class SenGyro extends NumericSensor
{
	Gyro sensor;

	/**
	 * Constructor for a generic gyroscopic sensor.
	 * 
	 * @param sensor A WPILib instance of the sensor, which should extend {@link Gyro}.
	 */
	public SenGyro(Gyro sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.getAngle();
	}
}
