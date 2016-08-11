package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class SenGyro extends NumericSensor
{
	Gyro sensor;
	
	public SenGyro(Gyro sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.getAngle();
	}
}
