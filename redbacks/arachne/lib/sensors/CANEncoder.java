package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableNumber;
import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class CANEncoder implements GettableNumber
{
	private final CANTalon talon;
	
	public CANEncoder(CANTalon talon) {
		this.talon = talon;
	}
	
	public CANEncoder(CtrlMotor talon) {
		this.talon = talon.controller;
	}
	
	public void reset() {
		set(0);
	}
	
	public void set(int value) {
		talon.setEncPosition(value);
	}
	
	public double get() {
		return talon.getEncPosition();
	}
}
