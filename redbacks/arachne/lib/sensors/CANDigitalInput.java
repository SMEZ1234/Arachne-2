package redbacks.arachne.lib.sensors;

import redbacks.arachne.lib.logic.GettableBoolean;
import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.CANTalon;

public class CANDigitalInput implements GettableBoolean
{
	private final CANTalon talon;
	private final boolean isForwardSwitch;
	
	public CANDigitalInput(CANTalon talon, boolean isForwardSwitch) {
		this.talon = talon;
		this.isForwardSwitch = isForwardSwitch;
	}
	
	public CANDigitalInput(CtrlMotor talon, boolean isForwardSwitch) {
		this.talon = talon.controller;
		this.isForwardSwitch = isForwardSwitch;
	}
	
	public void setInverted(boolean shouldInvert) {
		if(isForwardSwitch) talon.ConfigFwdLimitSwitchNormallyOpen(shouldInvert);
		else talon.ConfigRevLimitSwitchNormallyOpen(shouldInvert);
	}
	
	public boolean get() {
		return isForwardSwitch ? talon.isFwdLimitSwitchClosed() : talon.isRevLimitSwitchClosed();
	}
}
