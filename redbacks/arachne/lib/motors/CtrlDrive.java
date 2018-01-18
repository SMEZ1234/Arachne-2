package redbacks.arachne.lib.motors;

import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.lib.commands.CommandBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

/**
 * A replacement drive motor controller that is used to avoid conflicting instructions when individually setting motors on the drivetrain.
 * See {@link CtrlDrivetrain}.
 * 
 * @author Sean Zammit
 */
public class CtrlDrive extends CtrlMotor implements SpeedController
{
	/**
	 * Constructor for a drive motor controller used to avoid conflicting instructions when individually setting motors on the drivetrain.
	 * 
	 * @param motor The motor controller held inside this class. This is the one that is redirected to whenever a method in this class is used.
	 */
	public CtrlDrive(SpeedController motor) {
		super(motor);
	}

	public void set(double outputValue, CommandBase command) {
		lastCommand = command;
		speed = outputValue;
		ArachneRobot.isIndivDriveControl = true;
		if(!command.motorList.contains(this)) command.motorList.add(this);
	}

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
	 * Don't call this. It's there to be called by {@link RobotDriveBase}. To change the speed, use {@link #set(double, CommandBase)}.
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
