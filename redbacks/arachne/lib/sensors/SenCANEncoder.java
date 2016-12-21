package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * A generic encoder attached to a Talon SRX CAN controller.
 *
 * @author Sean Zammit
 */
public class SenCANEncoder extends NumericSensor
{
	private final CANTalon talon;

	/**
	 * Constructor for a generic encoder attached to a Talon SRX CAN controller.
	 * 
	 * @param talon The controller the encoder is attached to.
	 */
	public SenCANEncoder(CANTalon talon) {
		this.talon = talon;
	}

	/**
	 * Constructor for a generic encoder attached to a Talon SRX CAN controller.
	 * 
	 * @param talon The {@link CtrlMotor} instance holding the controller the encoder is attached to.
	 */
	public SenCANEncoder(CtrlMotor talon) {
		this.talon = (CANTalon) talon.controller;
	}

	protected double getSenVal() {
		return talon.getEncPosition();
	}
}
