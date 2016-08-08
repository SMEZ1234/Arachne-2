package redbacks.arachne.lib.motors;

import redbacks.arachne.core.CommandBase;
import redbacks.arachne.core.references.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;

/**
 * JAVADOC
 * A replacement motor controller to enable automatic stopping of motors by commands when they finish.
 * 
 * @author Sean Zammit
 */
public class CtrlDrive extends CtrlMotor
{
	/**
	 * @param motor The motor controller held inside this class. This is the one that is redirected to whenever a method in this class is used.
	 */
	public CtrlDrive(CANTalon motor) {
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
		RobotMap.isAutoController = true;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}
	
	/**
	 * Used to disable the motor when the command finishes.
	 */
	public void disable() {
		speed = 0;
	}
}
