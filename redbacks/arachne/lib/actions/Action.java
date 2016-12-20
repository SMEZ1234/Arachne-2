package redbacks.arachne.lib.actions;

import edu.wpi.first.wpilibj.Timer;
import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.commands.ComActionDependency;
import redbacks.arachne.lib.commands.CommandBase;

/**
 * The main action class. Provides a base for all actions to be built on.
 * 
 * @author Sean Zammit
 */
public class Action
{
	/** Flag to interrupt the command if the {@link #cancel() cancel()} method is called. */
	public boolean isInterrupted = false;
	
	/** Flag to show whether the action is currently being run. */
	public boolean isRunning = false;
	
	/** The check associated with the action that determines when the action is complete. */
	public final Check check;
	
	/** The command that is running this action. Set in {@link #initialise(CommandBase) initialise(CommandBase)}. */
	public CommandBase command;
	
	/** The time that this action began. Used for calculation of time spent running. */
	private double startTime = -1;
	
	/** 
	 * A list of any subsystems this action requires.
	 * This works in the same way that command requirements do, by creating new commands on any required subsystems that interrupt this action when interrupted.
	 */
	SubsystemBase[] systemDependencies;
	  
	/**
	 * Constructor for an action with a check to determine when it will finish.
	 * 
	 * @param check The check associated with the action that determines when the action is complete
	 */
	protected Action(Check check) {
		this.check = check;
	}
	
	/**
	 * Sets any subsystems that the action requires. See {@link #systemDependencies systemDependencies}.
	 * If called multiple times, it will override the previous list of dependencies.
	 * 
	 * @param subsystems All required subsystems.
	 * @return This action, so that you can call it on the constructor.
	 */
	public Action setDependencies(SubsystemBase... subsystems) {
		this.systemDependencies = subsystems;
		return this;
	}
	
	/**
	 * Checks whether the action is complete, and the command can move on to the next action.
	 * This method also checks another method, {@link #isDone() isDone()}, which can be overwritten by individual actions.
	 * 
	 * @return Whether the action should stop running.
	 */
	public final boolean isFinished() {
		return check.isFinished() || isInterrupted || isDone();
	}
	
	/**
	 * Runs repeatedly. This method is used to perform the action.
	 * This method also runs another method, {@link #onRun() onRun()}, which can be overwritten by individual actions.
	 */
	public final void execute() {
		check.onRun();
		onRun();
	}
	
	/**
	 * Runs when the action first starts. This method is used to handle anything that needs to be done before the action runs.
	 * This method also runs another method, {@link #onStart() onStart()}, which can be overwritten by individual actions.
	 * 
	 * @param command The command in which this action is being run.
	 */
	public final void initialise(CommandBase command) {
		this.command = command;
		check.initialise(command, this);
	    startTime = Timer.getFPGATimestamp();
	    isInterrupted = false;
	    isRunning = true;
	    if(systemDependencies != null) for(SubsystemBase subsystem : systemDependencies) new ComActionDependency(subsystem, this).start();
		onStart();
	}

	/**
	 * Runs when the action ends. This method is used to handle anything that needs to be done when the action finishes.
	 * This method also runs another method, {@link #onFinish() onFinish()}, which can be overwritten by individual actions.
	 */
	public final void end() {
		check.onFinish();
		isRunning = false;
		onFinish();
	}

	/**
	 * Used by individual actions to set other conditions for the action to be complete.
	 * 
	 * @return Whether the action should stop running.
	 */
	protected boolean isDone() {
		return false;
	}
	
	/**
	 * Used by individual actions to perform any operations while the action is running.
	 */
	protected void onRun() {}	
	
	/**
	 * Used by individual actions to perform any operations when the action first begins to run.
	 */
	protected void onStart() {}
	
	/**
	 * Used by individual actions to perform any operations when the action is complete.
	 */
	protected void onFinish() {}
	
	/**
	 * Interrupts this action when called.
	 * This will also interrupt the command that the action is run by.
	 */
	public final void cancel() {
		isInterrupted = true;
	}
	
	/**
	 * Returns the time since the action was initialised, in seconds.
	 * 
	 * @return The time since the action was initialised, in seconds.
	 */
	public final double timeSinceInitialized() {
	    return startTime < 0 ? 0 : Timer.getFPGATimestamp() - startTime;
	}
}
