package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class SenAnalog extends NumericSensor
{
	AnalogInput sensor;
	
	public SenAnalog(AnalogInput sensor) {
		this.sensor = sensor;
	}
	
	protected double getSenVal() {
		return sensor.getVoltage();
	}
}
