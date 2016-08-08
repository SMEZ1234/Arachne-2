package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.digital.ChBoolean;
import redbacks.arachne.lib.sensors.CANAnalog;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcSetCANAnalog extends Action
{
	public CANAnalog analogInput;
	public int value;
	
	public AcSetCANAnalog(CANAnalog analogInput, int value) {
		super(new ChBoolean(true));
		this.analogInput = analogInput;
		this.value = value;
	}

	public void onRun() {
		analogInput.set(value);
	}
}
