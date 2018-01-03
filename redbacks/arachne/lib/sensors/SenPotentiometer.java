package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * A generic potentiometer attached to an analog channel on the RIO.
 * If attached to a Talon SRX CAN controller, use SenCANAnalog in Arachne's CTRE sub-library.
 *
 * @author Sean Zammit
 */
public class SenPotentiometer extends NumericSensor
{
	AnalogPotentiometer sensor;

	/**
	 * Constructor for a generic potentiometer attached to an analog channel on the RIO.
	 * 
	 * @param sensor A WPILib instance of the potentiometer, which should extend {@link AnalogPotentiometer}.
	 */
	public SenPotentiometer(AnalogPotentiometer sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.get();
	}
}
