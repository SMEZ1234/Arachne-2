package redbacks.arachne.lib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class SenDI extends BinarySensor
{
	DigitalInput sensor;
	
	public SenDI(DigitalInput sensor) {
		this.sensor = sensor;
	}

	protected boolean getSenVal() {
		return sensor.get();
	}
}
