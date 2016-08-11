package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.Timer;

public class SenTimer extends NumericSensor
{
	public double getSenVal() {
		return Timer.getFPGATimestamp();
	}
}
