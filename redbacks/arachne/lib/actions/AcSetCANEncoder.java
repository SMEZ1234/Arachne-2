package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.digital.ChBoolean;
import redbacks.arachne.lib.sensors.CANEncoder;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcSetCANEncoder extends Action
{
	public CANEncoder enc;
	public int value;
	
	public AcSetCANEncoder(CANEncoder encoder, int value) {
		super(new ChBoolean(true));
		enc = encoder;
		this.value = value;
	}

	public void onRun() {
		enc.set(value);
	}
}
