package redbacks.arachne.lib.motors;

import redbacks.arachne.lib.commands.CommandBase;

/**
 * A replacement motor controller to enable automatic stopping of motors by commands when they finish.
 * 
 * @author Sean Zammit
 */
public class CtrlMotorList extends CtrlMotor
{
	/** The motor controller held inside this class. */
	public final CtrlMotor[] controllers;
	
	/**
	 * @param motor The motor controller held inside this class. This is the one that is redirected to whenever a method in this class is used.
	 */
	public CtrlMotorList(CtrlMotor... motors) {
		super(null);
		controllers = motors;
	}
	
	/**
	 * Used to set the value of the motor. Also tells the motor which command last set its speed.
	 * 
	 * @param outputValue The speed of the motor.
	 * @param command The command that last set the speed of the motor.
	 */
	public void set(double outputValue, CommandBase command) {
		for(CtrlMotor m : controllers) m.set(outputValue, command);
		lastCommand = command;
		speed = outputValue;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}
	
	/**
	 * Used to disable the motor when the command finishes.
	 */
	public void disable() {
		for(CtrlMotor m : controllers) m.disable();
		speed = 0;
	}
	
	/**
	 * Used to set the motor to not be stopped when the command that set it ends.
	 * 
	 * @return This motor. Reason being that it allows this method to be used in the same line as the constructor.
	 */
	public CtrlMotorList setUncancellable() {
		shouldCancel = false;
		return this;		
	}
}
