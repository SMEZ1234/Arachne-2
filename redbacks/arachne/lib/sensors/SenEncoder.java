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
	@Deprecated
	public SenEncoder(Encoder sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.get();
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

	public static class Rate extends SenEncoder
	{
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

	public static class Displacement extends SenEncoder
	{
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
