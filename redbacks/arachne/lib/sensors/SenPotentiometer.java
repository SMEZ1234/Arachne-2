package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class SenPotentiometer extends NumericSensor
{
	AnalogPotentiometer sensor;
	
	public SenPotentiometer(AnalogPotentiometer sensor) {
		this.sensor = sensor;
	}

	protected double getSenVal() {
		return sensor.get();
	}
}
