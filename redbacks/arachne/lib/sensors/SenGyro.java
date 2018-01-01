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
	@Deprecated
	public SenGyro(Gyro sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.getAngle();
	}

	public double pidGet() {
		switch(pidType) {
			case kDisplacement:
				return get();
			case kRate:
				return sensor.getRate();
			default:
				return 0.0;
		}
	}

	public static class Rate extends SenGyro
	{
		public Rate(Gyro sensor) {
			super(sensor);
		}
		
		protected double getSenVal() {
			return sensor.getRate();
		}
		
		public double pidGet() {
			return get();
		}
	}

	public static class Displacement extends SenGyro
	{
		public Displacement(Gyro sensor) {
			super(sensor);
		}
		
		protected double getSenVal() {
			return sensor.getAngle();
		}
		
		public double pidGet() {
			return get();
		}
	}
}
