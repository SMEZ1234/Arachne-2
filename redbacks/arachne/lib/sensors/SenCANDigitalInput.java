package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

public class SenCANDigitalInput extends BinarySensor
{
	private final CANTalon talon;
	private final boolean isForwardSwitch;
	
	public SenCANDigitalInput(CANTalon talon, boolean isForwardSwitch) {
		this.talon = talon;
		this.isForwardSwitch = isForwardSwitch;
	}
	
	public SenCANDigitalInput(CtrlMotor talon, boolean isForwardSwitch) {
		this.talon = (CANTalon) talon.controller;
		this.isForwardSwitch = isForwardSwitch;
	}
	
	public boolean getSenVal() {
		return isForwardSwitch ? talon.isFwdLimitSwitchClosed() : talon.isRevLimitSwitchClosed();
	}
}
