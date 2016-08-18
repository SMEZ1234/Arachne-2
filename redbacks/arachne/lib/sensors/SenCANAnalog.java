package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class SenCANAnalog extends NumericSensor
{
	private final CANTalon talon;
	
	public SenCANAnalog(CANTalon talon) {
		this.talon = talon;
	}
	
	public SenCANAnalog(CtrlMotor talon) {
		this.talon = (CANTalon) talon.controller;
	}
	
	protected double getSenVal() {
		return talon.getAnalogInPosition();
	}
}
