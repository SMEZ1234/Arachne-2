package redbacks.arachne.lib.motors;

import redbacks.arachne.lib.commands.CommandBase;

/**
 * A motor controller that groups other motor controllers so that they can be easily set.
 * 
 * @author Sean Zammit
 */
public class CtrlMotorList extends CtrlMotor
{
	/** The motor controllers grouped inside this class. */
	public final CtrlMotor[] controllers;

	/**
	 * Constructor for a grouping of motor controllers.
	 * 
	 * @param motors The motor controllers grouped inside this class. These are redirected to whenever a method in this class is used.
	 */
	public CtrlMotorList(CtrlMotor... motors) {
		super(null);
		controllers = motors;
	}

	public void set(double outputValue, CommandBase command) {
		for(CtrlMotor m : controllers) m.set(outputValue, command);
		lastCommand = command;
		speed = outputValue;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}

	public void disable() {
		for(CtrlMotor m : controllers) m.disable();
		speed = 0;
	}

	public CtrlMotorList setUncancellable() {
		shouldCancel = false;
		return this;
	}
}
