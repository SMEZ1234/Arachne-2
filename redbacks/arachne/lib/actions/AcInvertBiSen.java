package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.sensors.BinarySensor;

/**
 * An action to invert all readings from an instance of {@link BinarySensor BinarySensor}.
 * 
 * @author Sean Zammit
 */
public class AcInvertBiSen extends Action
{
	/** The BinarySensor being inverted by this action. */
	public BinarySensor sensor;
	private int type;

	/**
	 * Constructor for an action that will switch the current inversion state of a BinarySensor.
	 * 
	 * @param sensor The sensor to switch the inversion state of.
	 */
	public AcInvertBiSen(BinarySensor sensor) {
		super(new ChTrue());
		this.sensor = sensor;
		this.type = 2;
	}

	/**
	 * Constructor for an action that will set the inversion state of a BinarySensor to a specified state.
	 * 
	 * @param sensor The sensor being set.
	 * @param shouldInvert Whether the sensor's readings should be inverted.
	 */
	public AcInvertBiSen(BinarySensor sensor, boolean shouldInvert) {
		super(new ChTrue());
		this.sensor = sensor;
		this.type = shouldInvert ? 0 : 1;
	}

	public void onFinish() {
		sensor.setInverted(type == 0 ? true : type == 1 ? false : !sensor.isInverted);
	}
}
