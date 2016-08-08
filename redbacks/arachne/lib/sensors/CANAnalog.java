package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableNumber;
import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class CANAnalog implements GettableNumber
{
	private final CANTalon talon;
	
	public CANAnalog(CANTalon talon) {
		this.talon = talon;
	}
	
	public CANAnalog(CtrlMotor talon) {
		this.talon = talon.controller;
	}
	
	public void reset() {
		set(0);
	}
	
	public void set(int value) {
		talon.setAnalogPosition(value);
	}
	
	public double get() {
		return talon.getAnalogInPosition();
	}
}
