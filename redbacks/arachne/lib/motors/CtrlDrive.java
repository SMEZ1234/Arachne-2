package redbacks.arachne.lib.motors;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.commands.CommandBase;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * JAVADOC
 * A replacement motor controller to enable automatic stopping of motors by commands when they finish.
 * 
 * @author Sean Zammit
 */
public class CtrlDrive extends CtrlMotor implements SpeedController
{
	/**
	 * @param motor The motor controller held inside this class. This is the one that is redirected to whenever a method in this class is used.
	 */
	public CtrlDrive(SpeedController motor) {
		super(motor);
	}
	
	/**
	 * Used to set the value of the motor. Also tells the motor which command last set its speed.
	 * 
	 * @param outputValue The speed of the motor.
	 * @param command The command that last set the speed of the motor.
	 */
	public void set(double outputValue, CommandBase command) {
		lastCommand = command;
		speed = outputValue;
		ArachneRobot.isIndivDriveControl = true;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}
	
	/**
	 * Used to disable the motor when the command finishes.
	 */
	public void disable() {
		speed = 0;
		ArachneRobot.isIndivDriveControl = false;
	}

	/**
	 * Don't call this. Required SpeedController method.
	 */
	public void pidWrite(double output) {
		controller.pidWrite(output);
	}

	/**
	 * Don't call this. It's there to be called by RobotDrive. To change the speed, use set(double outputValue, CommandBase command).
	 */
	public void set(double speed, byte syncGroup) {
		this.speed = speed;
	}

	/**
	 * Don't call this. It's there to be called by RobotDrive. To change the speed, use set(double outputValue, CommandBase command).
	 */
	public void set(double speed) {
		this.speed = speed;
	}

	public void setInverted(boolean isInverted) {
		controller.setInverted(isInverted);
	}

	public boolean getInverted() {
		return controller.getInverted();
	}

	public void stopMotor() {
		controller.stopMotor();
		disable();
	}
}
