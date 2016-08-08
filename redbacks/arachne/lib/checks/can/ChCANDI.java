package redbacks.arachne.lib.checks.can;

import redbacks.arachne.lib.checks.Check.CheckDigital;
import redbacks.arachne.lib.sensors.CANDigitalInput;

/**
 * Checks the value of a sensor in a Digital Input wired into a CAN controller.
 * 
 * @author Sean Zammit
 */
public class ChCANDI extends CheckDigital {
	
	CANDigitalInput sensor;
	
	/**
	 * @param sensor The sensor being checked.
	 * @param isTriggered Whether the value should be true.
	 */
	public ChCANDI(CANDigitalInput sensor, boolean isTriggered) {
		super(isTriggered);
		this.sensor = sensor;
	}

	public boolean isDone() {
		return sensor.get();
	}
}