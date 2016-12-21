package redbacks.arachne.lib.motors;

import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.logic.GettableNumber;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * A generic motor controller class that can be used with other Arachne classes.
 * Is a {@link GettableNumber}, as well as adding a method to set whether it should stop running when the command that set it finishes.
 * 
 * @author Sean Zammit
 */
public class CtrlMotor implements GettableNumber
{
	/** The motor controller held inside this class. */
	public final SpeedController controller;

	/** The last command to set the value of this motor. Used by Arachne to automatically stop the motor when the command finishes. */
	public CommandBase lastCommand;

	/** Whether commands should automatically stop this motor when they finish. */
	public boolean shouldCancel = true;

	protected double speed = 0;

	/**
	 * Constructor for a generic motor controller used in other Arachne classes.
	 * 
	 * @param motor The motor controller held inside this class. This is the one that is redirected to whenever a method in this class is used.
	 */
	public CtrlMotor(SpeedController motor) {
		controller = motor;
	}

	/**
	 * Used to set the speed of the motor.
	 * Also tells the motor which command last set its speed, so that it can be disabled when the command finishes.
	 * 
	 * @param outputValue The speed of the motor.
	 * @param command The command that last set the speed of the motor.
	 */
	public void set(double outputValue, CommandBase command) {
		controller.set(outputValue);
		lastCommand = command;
		speed = outputValue;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}

	/**
	 * Used to disable the motor when the command finishes.
	 */
	public void disable() {
		controller.set(0);
		speed = 0;
	}

	/**
	 * Used to set the motor to not be stopped when the command that set it ends.
	 * 
	 * @return This motor, so that you can call it on the constructor.
	 */
	public CtrlMotor setUncancellable() {
		shouldCancel = false;
		return this;
	}

	public double get() {
		return speed;
	}

	/**
	 * Inverts the output from the motor.
	 * 
	 * @param isInverted Whether the motor output should be inverted.
	 */
	public void setInverted(boolean isInverted) {
		controller.setInverted(isInverted);
	}

	/**
	 * Gets whether the motor output is inverted.
	 * 
	 * @return Whether the motor output is inverted.
	 */
	public boolean getInverted() {
		return controller.getInverted();
	}
}
