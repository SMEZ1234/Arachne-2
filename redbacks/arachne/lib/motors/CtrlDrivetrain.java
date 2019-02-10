package redbacks.arachne.lib.motors;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import redbacks.arachne.core.ArachneRobot;

/**
 * A replacement for {@link DifferentialDrive} that allows motor speeds to be set individually without having conflicting instructions from the DifferentialDrive methods.
 * 
 * @author Sean Zammit
 */
public class CtrlDrivetrain extends DifferentialDrive
{
	/** Used by Arachne to send speed values from all instances of CtrlDrivetrain to the drive motors. */
	public static ArrayList<CtrlDrivetrain> drivetrains = new ArrayList<CtrlDrivetrain>();

	public SpeedController m_leftMotor;
	public SpeedController m_rightMotor;
	
	/**
	 * Constructor for a drivetrain that has two non-slave motors, both of which must be instances of {@link CtrlDrive}.
	 * 
	 * @param leftMotor The left CtrlDrive object used to drive the robot.
	 * @param rightMotor The right CtrlDrive object used to drive the robot.
	 */
	public CtrlDrivetrain(CtrlDrive leftMotor, CtrlDrive rightMotor) {
		super(leftMotor, rightMotor);
		this.m_leftMotor = leftMotor;
		this.m_rightMotor = rightMotor;
		drivetrains.add(this);
	}

	/**
	 * Called by Arachne while the robot is enabled on all instances of CtrlDrivetrain to send speed values to the drive motors.
	 */
	public void passControllerOutputs() {
		((CtrlDrive) m_leftMotor).controller.set(m_leftMotor.get() * m_maxOutput);
		((CtrlDrive) m_rightMotor).controller.set(m_rightMotor.get() * m_maxOutput);

		if(ArachneRobot.isIndivDriveControl) feed();
	}
}
