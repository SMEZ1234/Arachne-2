package redbacks.arachne.lib.motors;

import redbacks.arachne.lib.commands.CommandRB;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * A replacement motor controller to enable automatic stopping of motors by commands when they finish.
 * 
 * @author Sean Zammit
 */
public class MotorControllerRB extends CANTalon
{
	/** The last command to set the value of this motor. Used to automatically stop the motor when the command finishes. */
	public CommandRB lastCommand;
	
	/** Whether commands should automatically stop this motor when they finish. Disable for motors like the polycord. */
	public boolean shouldCancel = true;
	
	public MotorControllerRB(int deviceNumber) {
		super(deviceNumber);
	}
	
	/**
	 * DO NOT USE THIS METHOD. BAD STUFF HAPPENS.
	 * 
	 * More specifically, it doesn't set the command. This means that the command will not turn off the motor when it finishes.
	 */
	public void set(double outputValue) {
		super.set(outputValue);
	}
	
	/**
	 * Used to set the value of the motor. Also tells the motor which command last set its speed.
	 * 
	 * @param outputValue The speed of the motor.
	 * @param command The command that last set the speed of the motor.
	 */
	public void set(double outputValue, CommandRB command) {
		this.set(outputValue);
		lastCommand = command;
		command.motorList.add(this);
	}
	
	/**
	 * Used to set the motor to not be stopped when the command that set it ends.
	 * 
	 * @return This motor. Reason being that it allows this method to be used in the same line as the constructor.
	 */
	public MotorControllerRB setUncancellable() {
		shouldCancel = false;
		return this;		
	}
}
