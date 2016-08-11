package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.sensors.BinarySensor;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcInvertBiSen extends Action
{
	public BinarySensor sensor;
	public int type;
	
	public AcInvertBiSen(BinarySensor sensor) {
		super(new ChTrue());
		this.sensor = sensor;
		this.type = 2;
	}
	
	public AcInvertBiSen(BinarySensor sensor, boolean shouldInvert) {
		super(new ChTrue());
		this.sensor = sensor;
		this.type = shouldInvert ? 0 : 1;
	}

	public void onFinish() {
		sensor.setInverted(type == 0 ? true : type == 1 ? false : !sensor.isInverted);
	}
}
