package redbacks.arachne.core;

import redbacks.arachne.core.api.RobotAPI;
import redbacks.arachne.core.api.RobotAPI.Order;
import redbacks.arachne.core.api.RobotExtender;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.motors.CtrlDrivetrain;
import redbacks.arachne.lib.motors.CtrlMecanumDrivetrain;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is Arachne's robot superclass. It handles everything required by Arachne to run, and removes as much code as possible from the robot class.
 * 
 * @author Sean Zammit
 */
public abstract class ArachneRobot extends TimedRobot
{
	public static boolean isAuto, isIndivDriveControl = false;

	public static boolean interruptAtAuto = true, interruptAtTele = true, interruptAtDisable = true;
	public static boolean executeInAuto = true, executeInTele = true, executeInDisable = false;

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
	
	public final void robotPeriodic() {
		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.executeRobot();
		executeRobot();
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.executeRobot();
	}

	public final void autonomousInit() {
		if(interruptAtAuto) Scheduler.getInstance().removeAll();
		isAuto = true;

		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.initialiseAuto();
		
		//Sets the autonomous sequence number.
		int av = (int) SmartDashboard.getNumber("Auto Version", 0);
		SmartDashboard.putNumber("Auto Version", av);

		//Starts the autonomous sequence.
		CommandBase auto = getAutonomous(av);
		if(auto != null) auto.start();
		initialiseAuto();
		
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.initialiseAuto();
	}

	public final void autonomousPeriodic() {
		if(executeInAuto) Scheduler.getInstance().run();

		//Handles all Arachne drivetrains.
		for(CtrlDrivetrain drive : CtrlDrivetrain.drivetrains) drive.passControllerOutputs();
		for(CtrlMecanumDrivetrain drive : CtrlMecanumDrivetrain.drivetrains) drive.passControllerOutputs();

		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.executeAuto();
		executeAuto();
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.executeAuto();
	}

	public final void teleopInit() {
		if(interruptAtTele) Scheduler.getInstance().removeAll();
		isAuto = false;

		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.initialiseTeleop();
		initialiseTeleop();
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.initialiseTeleop();
	}

	public final void teleopPeriodic() {
		if(executeInTele) Scheduler.getInstance().run();

		//Handles all Arachne drivetrains.
		for(CtrlDrivetrain drive : CtrlDrivetrain.drivetrains) drive.passControllerOutputs();
		for(CtrlMecanumDrivetrain drive : CtrlMecanumDrivetrain.drivetrains) drive.passControllerOutputs();

		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.executeTeleop();
		executeTeleop();
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.executeTeleop();
	}

	public final void disabledInit() {
		if(interruptAtDisable) Scheduler.getInstance().removeAll();
		
		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.initialiseDisabled();
		initialiseDisabled();
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.initialiseDisabled();
	}

	public final void disabledPeriodic() {
		if(executeInDisable) Scheduler.getInstance().run();
		
		for(RobotExtender extension : RobotAPI.getExtensions(Order.PRE)) extension.executeDisabled();
		executeDisabled();
		for(RobotExtender extension : RobotAPI.getExtensions(Order.POST)) extension.executeDisabled();
	}

	/** Called once when the robot code starts. Replacement for {@link #robotInit() robotInit}. */
	public void initialiseRobot() {}

	/** Called repeatedly during all modes. Replacement for {@link #robotPeriodic() robotPeriodic}. */
	public void executeRobot() {}

	/** Called once when autonomous starts. Replacement for {@link #autonomousInit() autonomousInit}. */
	public void initialiseAuto() {}

	/** Called repeatedly during autonomous. Replacement for {@link #autonomousPeriodic() autonomousPeriodic}. */
	public void executeAuto() {}

	/** Called once when teleop starts. Replacement for {@link #teleopInit() teleopInit}. */
	public void initialiseTeleop() {}

	/** Called repeatedly during teleop. Replacement for {@link #teleopPeriodic() teleopPeriodic}. */
	public void executeTeleop() {}

	/** Called once when the robot is disabled. Replacement for {@link #disabledInit() disabledInit}. */
	public void initialiseDisabled() {}

	/** Called repeatedly while the robot is disabled. Replacement for {@link #disabledPeriodic() disabledPeriodic}. */
	public void executeDisabled() {
		Timer.delay(0.001);
	}

	/** A required method to set the default commands for each subsystem. Called during {@link #robotInit() robotInit}. */
	public abstract void initDefaultCommands();

	/**
	 * A required method to determine the autonomous that will run based on the ID given. Called by Arachne at the beginning of autonomous.
	 * 
	 * @param autoID The autonomous version number.
	 * @return The autonomous command to run.
	 */
	public abstract CommandBase getAutonomous(int autoID);
}
