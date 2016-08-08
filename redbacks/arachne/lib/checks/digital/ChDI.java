package redbacks.arachne.lib.checks.digital;

import edu.wpi.first.wpilibj.DigitalInput;
import redbacks.arachne.lib.checks.Check.CheckDigital;

/**
 * Checks the value of a sensor in a Digital Input.
 * 
 * @author Sean Zammit
 */
public class ChDI extends CheckDigital {
	
	DigitalInput sensor;
	
	/**
	 * @param sensor The sensor being checked.
	 * @param isTriggered Whether the value should be true.
	 */
	public ChDI(DigitalInput sensor, boolean isTriggered) {
		super(isTriggered);
		this.sensor = sensor;
	}

	public boolean isDone() {
		return sensor.get() == type;
	}
}