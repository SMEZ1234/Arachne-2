package redbacks.arachne.core;

import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.motors.CtrlDrivetrain;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is Arachne's robot superclass. It handles everything required by Arachne to run, and removes as much code as possible from the robot class.
 * 
 * @author Sean Zammit
 */
public abstract class ArachneRobot extends IterativeRobot
{
	public static boolean isAuto,
			isIndivDriveControl = false;

	/** Contains all subsystems on the robot, and allows for all commands requiring subsystems to be cancelled. */
	public static ArrayList<SubsystemBase> subsystemList = new ArrayList<SubsystemBase>();

	/**
	 * Exists so that complicated sequences involving many systems on the robot can be interrupted by another sequence.
	 * It should be a requirement for any large sequences, as long as said sequences are not subsequences of other sequences requiring this subsystem.
	 */
	public static SubsystemBase sequencer = new SubsystemBase();

	public final void robotInit() {
		initDefaultCommands();
		initialiseRobot();
	}

	public final void autonomousInit() {
		isAuto = true;

		//Sets the autonomous sequence number.
		int av = (int) SmartDashboard.getNumber("Auto Version", 0);
		SmartDashboard.putNumber("Auto Version", av);

		//Starts the autonomous sequence.
		CommandBase auto = getAutonomous(av);
		if(auto != null) auto.start();
		initialiseRobot();
	}

	public final void autonomousPeriodic() {
		Scheduler.getInstance().run();
		executeAuto();
	}

	public final void teleopInit() {
		Scheduler.getInstance().removeAll();
		isAuto = false;
		initialiseTeleop();
	}

	public final void teleopPeriodic() {
		Scheduler.getInstance().run();

		//Handles all Arachne drivetrains.
		for(CtrlDrivetrain drive : CtrlDrivetrain.drivetrains) drive.passControllerOutputs();

		executeTeleop();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

	public void disabledInit() {}

	public void disabledPeriodic() {
		Timer.delay(0.001);
	}

	/** Called once when the robot code starts. Replacement for {@link #robotInit() robotInit}. */
	public void initialiseRobot() {}

	/** Called once when autonomous starts. Replacement for {@link #autonomousInit() autonomousInit}. */
	public void initialiseAuto() {}

	/** Called repeatedly during autonomous. Replacement for {@link #autonomousPeriodic() autonomousPeriodic}. */
	public void executeAuto() {}

	/** Called once when teleop starts. Replacement for {@link #teleopInit() teleopInit}. */
	public void initialiseTeleop() {}

	/** Called repeatedly during teleop. Replacement for {@link #teleopPeriodic() teleopPeriodic}. */
	public void executeTeleop() {}

	/** A required method to set the default commands for each subsystem. Called during {@link #robotInit() robotInit}. */
	public abstract void initDefaultCommands();

	/**
	 * A required method to determine the autonomous that will run based on the ID given. Called by Arachne at the beginning of autonomous.
	 * 
	 * @param autoID The autonomous version number.
	 */
	public abstract CommandBase getAutonomous(int autoID);
}
