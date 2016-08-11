package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.Encoder;

public class SenEncoder extends NumericSensor
{
	Encoder sensor;
	
	public SenEncoder(Encoder sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.get();
	}
}
