package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * A generic gyroscopic sensor.
 *
 * @author Sean Zammit
 */
public abstract class SenGyro extends NumericSensor
{
	Gyro sensor;

	private SenGyro(Gyro sensor) {
		this.sensor = sensor;
	}

	/**
	 * A generic gyroscopic sensor measuring rate of change.
	 *
	 * @author Sean Zammit
	 */
	public static class Rate extends SenGyro
	{
		/**
		 * Constructor for a generic gyroscopic sensor measuring rate of change.
		 * 
		 * @param sensor A WPILib instance of the sensor, which should extend {@link Gyro}.
		 */
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

	/**
	 * A generic gyroscopic sensor measuring displacement from an initial position.
	 *
	 * @author Sean Zammit
	 */
	public static class Displacement extends SenGyro
	{
		/**
		 * Constructor for a generic gyroscopic sensor measuring displacement from an initial position.
		 * 
		 * @param sensor A WPILib instance of the sensor, which should extend {@link Gyro}.
		 */
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
