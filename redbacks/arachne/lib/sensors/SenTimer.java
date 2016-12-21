package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.Timer;

/**
 * A timer that functions as a sensor.
 * You should {@link NumericSensor#set(double) set} the value to 0 before using it, as otherwise it will just give you the system clock reading.
 *
 * @author Sean Zammit
 */
public class SenTimer extends NumericSensor
{
	public double getSenVal() {
		return Timer.getFPGATimestamp();
	}
}
