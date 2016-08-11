package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.digital.ChBoolean;
import redbacks.arachne.lib.sensors.BinarySensor;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcInvertBinarySensor extends Action
{
	public BinarySensor sensor;
	public int type;
	
	public AcInvertBinarySensor(BinarySensor sensor) {
		super(new ChBoolean(true));
		this.sensor = sensor;
		this.type = 2;
	}
	
	public AcInvertBinarySensor(BinarySensor sensor, boolean shouldInvert) {
		super(new ChBoolean(true));
		this.sensor = sensor;
		this.type = shouldInvert ? 0 : 1;
	}

	public void onFinish() {
		sensor.setInverted(type == 0 ? true : type == 1 ? false : !sensor.isInverted);
	}
}
