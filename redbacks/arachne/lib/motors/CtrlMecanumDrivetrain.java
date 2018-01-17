package redbacks.arachne.lib.motors;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import redbacks.arachne.core.ArachneRobot;

/**
 * A replacement for {@link MecanumDrive} that allows motor speeds to be set individually without having conflicting instructions from the MecanumDrive methods.
 * 
 * @author Sean Zammit
 */
public class CtrlMecanumDrivetrain extends MecanumDrive
{
	/** Used by Arachne to send speed values from all instances of CtrlMecanumDrivetrain to the drive motors. */
	public static ArrayList<CtrlMecanumDrivetrain> drivetrains = new ArrayList<CtrlMecanumDrivetrain>();

	public SpeedController m_frontLeftMotor;
	public SpeedController m_frontRightMotor;
	public SpeedController m_rearLeftMotor;
	public SpeedController m_rearRightMotor;
	
	/**
	 * Constructor for a drivetrain that has four non-slave motors, all of which must be instances of {@link CtrlDrive}.
	 * 
	 * @param frontLeftMotor The front left CtrlDrive object used to drive the robot.
	 * @param rearLeftMotor The rear left CtrlDrive object used to drive the robot.
	 * @param frontRightMotor The front right CtrlDrive object used to drive the robot.
	 * @param rearRightMotor The rear right CtrlDrive object used to drive the robot.
	 */
	public CtrlMecanumDrivetrain(CtrlDrive frontLeftMotor, CtrlDrive rearLeftMotor, CtrlDrive frontRightMotor, CtrlDrive rearRightMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.m_frontLeftMotor = frontLeftMotor;
		this.m_frontRightMotor = frontRightMotor;
		this.m_rearLeftMotor = rearLeftMotor;
		this.m_rearRightMotor = rearRightMotor;
		drivetrains.add(this);
	}

	/**
	 * Called by Arachne while the robot is enabled on all instances of CtrlMecanumDrivetrain to send speed values to the drive motors.
	 */
	public void passControllerOutputs() {
		((CtrlDrive) m_frontLeftMotor).controller.set(m_frontLeftMotor.get() * m_maxOutput);
		((CtrlDrive) m_frontRightMotor).controller.set(m_frontRightMotor.get() * m_maxOutput);
		((CtrlDrive) m_rearLeftMotor).controller.set(m_rearLeftMotor.get() * m_maxOutput);
		((CtrlDrive) m_rearRightMotor).controller.set(m_rearRightMotor.get() * m_maxOutput);

		if(ArachneRobot.isIndivDriveControl) {
			if(m_safetyHelper != null) m_safetyHelper.feed();
		}
	}
}