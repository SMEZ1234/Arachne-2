package redbacks.arachne.lib.motors;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import redbacks.arachne.core.ArachneRobot;

/**
 * A replacement for {@link RobotDrive} that allows motor speeds to be set individually without having conflicting instructions from the RobotDrive methods.
 * 
 * @author Sean Zammit
 */
public class CtrlDrivetrain extends RobotDrive
{
	/** Used by Arachne to send speed values from all instances of CtrlDrivetrain to the drive motors. */
	public static ArrayList<CtrlDrivetrain> drivetrains = new ArrayList<CtrlDrivetrain>();

	/**
	 * Constructor for a drivetrain that has two motors, both of which must be instances of {@link CtrlDrive}.
	 * 
	 * @param leftMotor The left CtrlDrive object used to drive the robot.
	 * @param rightMotor The right CtrlDrive object used to drive the robot.
	 */
	public CtrlDrivetrain(CtrlDrive leftMotor, CtrlDrive rightMotor) {
		super(leftMotor, rightMotor);
		drivetrains.add(this);
	}

	/**
	 * Constructor for a drivetrain that has four motors, all of which must be instances of {@link CtrlDrive}.
	 * 
	 * @param frontLeftMotor The front left CtrlDrive object used to drive the robot.
	 * @param rearLeftMotor The rear left CtrlDrive object used to drive the robot.
	 * @param frontRightMotor The front right CtrlDrive object used to drive the robot.
	 * @param rearRightMotor The rear right CtrlDrive object used to drive the robot.
	 */
	public CtrlDrivetrain(CtrlDrive frontLeftMotor, CtrlDrive rearLeftMotor, CtrlDrive frontRightMotor, CtrlDrive rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		drivetrains.add(this);
	}

	/**
	 * Called by Arachne while the robot is enabled on all instances of CtrlDrivetrain to send speed values to the drive motors.
	 */
	public void passControllerOutputs() {
		if(m_frontLeftMotor != null) {
			((CtrlDrive) m_frontLeftMotor).controller.set(m_frontLeftMotor.get() * m_maxOutput, m_syncGroup);
		}
		((CtrlDrive) m_rearLeftMotor).controller.set(m_rearLeftMotor.get() * m_maxOutput, m_syncGroup);

		if(m_frontRightMotor != null) {
			((CtrlDrive) m_frontRightMotor).controller.set(m_frontRightMotor.get() * m_maxOutput, m_syncGroup);
		}
		((CtrlDrive) m_rearRightMotor).controller.set(m_rearRightMotor.get() * m_maxOutput, m_syncGroup);

		if(ArachneRobot.isIndivDriveControl) {
			if(this.m_syncGroup != 0) CANJaguar.updateSyncGroup(m_syncGroup);
			if(m_safetyHelper != null) m_safetyHelper.feed();
		}
	}
}
