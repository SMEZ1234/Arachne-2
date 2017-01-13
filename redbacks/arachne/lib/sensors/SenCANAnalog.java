package redbacks.arachne.lib.sensors;

import com.ctre.CANTalon;

import redbacks.arachne.lib.motors.CtrlMotor;

/**
 * A generic analog sensor attached to a Talon SRX CAN controller.
 *
 * @author Sean Zammit
 */
public class SenCANAnalog extends NumericSensor
{
	private final CANTalon talon;
	
	/**
	 * Constructor for a generic analog sensor attached to a Talon SRX CAN controller.
	 * 
	 * @param talon The controller the sensor is attached to.
	 */
	public SenCANAnalog(CANTalon talon) {
		this.talon = talon;
	}

	/**
	 * Constructor for a generic analog sensor attached to a Talon SRX CAN controller.
	 * 
	 * @param talon The {@link CtrlMotor} instance holding the controller the sensor is attached to.
	 */
	public SenCANAnalog(CtrlMotor talon) {
		this.talon = (CANTalon) talon.controller;
	}

	protected double getSenVal() {
		return talon.getAnalogInPosition();
	}
}
