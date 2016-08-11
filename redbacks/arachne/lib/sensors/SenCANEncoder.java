package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class SenCANEncoder extends NumericSensor
{
	private final CANTalon talon;
	
	public SenCANEncoder(CANTalon talon) {
		this.talon = talon;
	}
	
	public SenCANEncoder(CtrlMotor talon) {
		this.talon = talon.controller;
	}
	
	protected double getSenVal() {
		return talon.getEncPosition();
	}
}
