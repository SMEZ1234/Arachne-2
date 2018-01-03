package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.Encoder;

/**
 * A generic encoder attached to digital ports on the RIO.
 *
 * @author Sean Zammit
 */
public abstract class SenEncoder extends NumericSensor
{
	Encoder sensor;

	private SenEncoder(Encoder sensor) {
		this.sensor = sensor;
	}

	/**
	 * A generic encoder measuring rate of change, attached to digital ports on the RIO.
	 *
	 * @author Sean Zammit
	 */
	public static class Rate extends SenEncoder
	{
		/**
		 * Constructor for a generic encoder measuring rate of change, attached to digital ports on the RIO.
		 * 
		 * @param sensor A WPILib instance of the encoder, which should extend {@link Encoder}.
		 */
		public Rate(Encoder sensor) {
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
	 * A generic encoder measuring displacement from an initial position, attached to digital ports on the RIO.
	 *
	 * @author Sean Zammit
	 */
	public static class Displacement extends SenEncoder
	{
		/**
		 * Constructor for a generic encoder measuring displacement from an initial position, attached to digital ports on the RIO.
		 * 
		 * @param sensor A WPILib instance of the encoder, which should extend {@link Encoder}.
		 */
		public Displacement(Encoder sensor) {
			super(sensor);
		}
		
		protected double getSenVal() {
			return sensor.get();
		}
		
		public double pidGet() {
			return get();
		}
	}
}
