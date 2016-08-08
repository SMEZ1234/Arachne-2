package redbacks.arachne.lib.checks.analog;

import redbacks.arachne.lib.checks.Check.CheckAnalog;
import redbacks.arachne.lib.motors.CtrlMotor;

/**
 * Checks whether a motor has reached a specific speed.
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class ChMotor extends CheckAnalog
{
	CtrlMotor motor;
	
	public ChMotor(double value, CtrlMotor motor) {
		this(value, motor, true);
	}
	
	public ChMotor(double value, CtrlMotor motor, boolean isGreaterThan) {
		this(value, motor, isGreaterThan, true);
	}

	public ChMotor(double value, CtrlMotor motor, boolean isGreaterThan, boolean useAbsoluteReading) {
		super(value, isGreaterThan, useAbsoluteReading, false);
		this.motor = motor;
	}
	
	public double getAnalogValue() {
		return motor.get();
	}
}
