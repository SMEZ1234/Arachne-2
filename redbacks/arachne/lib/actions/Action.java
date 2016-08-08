package redbacks.arachne.lib.actions;

import edu.wpi.first.wpilibj.Timer;
import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.commands.ComActionDependency;
import redbacks.arachne.lib.subsystems.SubsystemBase;

/**
 * The main action class. Provides a base for all actions to be built on.
 * Do not create instances of this class.
 * 
 * JAVADOC Method names.
 * 
 * @author Sean Zammit
 */
public class Action
{
	public boolean isInterrupted = false;
	public boolean isRunning = false;
	
	/** The check associated with the action that determines when the action is complete */
	public final Check check;
	
	public CommandBase command;
	
	/** The time that this action began. Used for calculation of time spent running. */
	private double startTime = -1;
	  
	/**
	 * @param check The check associated with the action that determines when the action is complete
	 */
	protected Action(Check check) {
		this.check = check;
	}
	
	/**
	 * JAVADOC
	 */
	public Action setDependencies(SubsystemBase... subsystems) {
		for(SubsystemBase subsystem : subsystems) new ComActionDependency(subsystem, this);
		return this;
	}
	
	/**
	 * Checks whether the action is complete, and the command can move on to the next action.
	 * This method passes the check along to another method (isComplete) which can be overwritten. Non-overwritable code goes here.
	 * 
	 * @param command The command in which this action is being run.
	 * @return Whether the action should stop running.
	 */
	public final boolean isFinished() {
		return check.isFinished() || isInterrupted || isDone();
	}
	
	/**
	 * Runs every loop. This method is used to perform the action.
	 * This method also runs another method (runAction) which can be overwritten. Non-overwritable code goes here.
	 * 
	 * @param command The command in which this action is being run.
	 */
	public final void periodic() {
		check.onRun();
		onRun();
	}
	
	/**
	 * Runs when the action first starts. This method is used to handle anything that needs to be done before the action runs.
	 * This method also runs another method (onStart) which can be overwritten. Non-overwritable code goes here.
	 * 
	 * @param command The command in which this action is being run.
	 */
	public final void initialise(CommandBase command) {
		this.command = command;
		check.initialise(command, this);
	    startTime = Timer.getFPGATimestamp();
	    isInterrupted = false;
	    isRunning = true;
		onStart();
	}

	/**
	 * Runs when the action ends. This method is used to handle anything that needs to be done when the command finishes.
	 * This method also runs another method (onFinish) which can be overwritten. Non-overwritable code goes here.
	 * 
	 * @param command The command in which this action is being run.
	 */
	public final void end() {
		check.onFinish();
		isRunning = false;
		onFinish();
	}

	/**
	 * Checks whether the action is complete, and the command can move on to the next action.
	 * This method can be individually set by each action. Do not call it. Ever.
	 * 
	 * @param command The command in which this action is being run.
	 * @return Whether the action should stop running.
	 */
	protected boolean isDone() {
		return false;
	}
	
	/**
	 * Runs every loop. This method is used to perform the action.
	 * This method can be individually set by each action. Do not call it. Ever.
	 * 
	 * @param command The command in which this action is being run.
	 */
	protected void onRun() {}	
	
	/**
	 * Runs when the action first starts. This method is used to handle anything that needs to be done before the command runs.
	 * This method can be individually set by each action. Do not call it. Ever.
	 * 
	 * @param command The command in which this action is being run.
	 */
	protected void onStart() {}
	
	/**
	 * Runs when the action ends. This method is used to handle anything that needs to be done when the command finishes.
	 * This method can be individually set by each action. Do not call it. Ever.
	 * 
	 * @param command The command in which this action is being run.
	 */
	protected void onFinish() {}
	
	//JAVADOC
	public final void cancel() {
		isInterrupted = true;
	}
	
	//JAVADOC
	public final double timeSinceInitialized() {
	    return startTime < 0 ? 0 : Timer.getFPGATimestamp() - startTime;
	}
}
