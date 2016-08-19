package redbacks.arachne.core;

import redbacks.arachne.lib.motors.CtrlDrivetrain;
import redbacks.arachne.lib.subsystems.SubsystemBase;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource directory.
 */
public abstract class ArachneRobot extends IterativeRobot
{
	/** This is used to provide public access to whether the robot is in autonomous or teleoperated mode. */
	public static boolean 
		isAuto,
		isIndivDriveControl = false;

	/**
	 * A full list of subsystems on the robot.
	 * Arachne only uses this list when interrupting all subsystems on the robot, but it's able to be used for other purposes as well, such as determining which subsystems are currently busy, etc.
	 */
	public static ArrayList<SubsystemBase> subsystemList = new ArrayList<SubsystemBase>();
	
	/** Exists so that complicated sequences involving many systems on the robot can be interrupted by another sequence. */
	public static SubsystemBase sequencer = new SubsystemBase();
	
	/**
	 * This function is run when the robot is first started up and should be used for any initialization code.
	 */
	public final void robotInit() {
		initDefaultCommands();
		initialiseRobot();
	}

	public final void autonomousInit() {
		isAuto = true;
		
		//This sets the command used to begin the autonomous sequence
		int av = (int) SmartDashboard.getNumber("Autonomous Version", 0);
		SmartDashboard.putNumber("Autonomous Version", av);

		//This starts the autonomous sequence.
		getAutonomous(av).start();
		initialiseRobot();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	public final void autonomousPeriodic() {
		Scheduler.getInstance().run();
		executeAuto();
	}

	public final void teleopInit() {
		Scheduler.getInstance().removeAll();
		isAuto = false;
		initialiseTeleop();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	public final void teleopPeriodic() {
		Scheduler.getInstance().run();
		for(CtrlDrivetrain drive : CtrlDrivetrain.drivetrains) drive.passControllerOutputs();
		executeTeleop();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void initialiseRobot() {}
	public void initialiseAuto() {}
	public void executeAuto() {}
	public void initialiseTeleop() {}
	public void executeTeleop() {}
	
	public abstract void initDefaultCommands();
	public abstract CommandBase getAutonomous(int autoID);
}
