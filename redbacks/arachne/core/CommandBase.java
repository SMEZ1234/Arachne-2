package redbacks.arachne.core;

import java.util.ArrayList;

import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChQueue;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.motors.CtrlMotor;
import redbacks.arachne.lib.subsystems.SubsystemBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 * CommandBase creates and stores each subsystem and the instance of the operator interface, or OI.
 * It also creates a standard setup for all commands. Because each command in Arachne is simply a sequence of actions, you should never need to create a command.
 * If you really must create a command, make sure that it inherits CommandBase.
 * 
 * @author Sean Zammit
 */
public class CommandBase extends Command
{
	/**	The sequence of actions. This is never changed. */
	private final ArrayList<Action> actionSeq = new ArrayList<Action>();
	
	/** The position in the list of actions that the command is up to. */
	int actionPos;
	
	/** The list of motors that this command has set the speed of. */
	public ArrayList<CtrlMotor> motorList = new ArrayList<CtrlMotor>();

	/** A boolean used to determine whether the command should stop running when the button to trigger it is released. */
	private boolean isWhileHeld = false;
	
	/** The button that, when released, will stop the command. It does not necessarily have to be the same button that activated it. */
	private Button button;
	
	public SubsystemBase requiredSubsystem;
	
	/**
	 * @param requiredSystem The subsystem that this command will run on, or null if none are required.
	 * @param actions The list of actions that are run in sequence by the command.
	 */
	public CommandBase(SubsystemBase requiredSystem, Action... actions) {
		super();
		if(requiredSystem != null) requires(requiredSystem);
		if(actions.length > 0) for(Action action : actions) this.actionSeq.add(action);
		else this.actionSeq.add(new AcDoNothing(new ChTrue()));
	}
	
	public void requires(SubsystemBase subsystem) {
		super.requires(subsystem);
		requiredSubsystem = subsystem;
	}

	protected final void initialize() {	this.onStart(); }
	
	protected final void end() {
		this.onFinish();
		for(CtrlMotor motor : motorList) if(motor.lastCommand == this && motor.shouldCancel)
			motor.disable();
		motorList.clear();
	}
	
	protected void interrupted() { this.end(); }
	
	protected final boolean isFinished() {
		return (isWhileHeld && !button.get()) || isDone();
	}

	/**
	 * Called when the command begins.
	 */
	protected void onStart() {
		actionPos = 0;
		actionSeq.get(0).initialise(this);
		if(requiredSubsystem != null && this != requiredSubsystem.getDefaultCommand()) requiredSubsystem.interruptRelatedSubsystems();
	}
	
	protected void execute() {
		if(actionSeq.size() > actionPos) {
			Action action = actionSeq.get(actionPos);
			
			if(!action.isRunning) action.initialise(this);
			action.execute();
			if(action.isFinished() || (action.check instanceof ChQueue && actionSeq.size() > actionPos + 1)) {
				action.end();
				actionPos++;
			}
			if(action.isInterrupted) this.cancel();
		}
	}

	/**
	 * Called when the command finishes.
	 */
	protected void onFinish() {}
	
	/**
	 * Called to check whether the command should end. Replaces isFinished(), so that buttons can stop commands when they are released.
	 * 
	 * @return Whether the command should end.
	 */
	public boolean isDone() {
		return actionSeq.size() <= actionPos;
	}

	/**
	 * Used to set a command to run only until a button is released.
	 * 
	 * @return This command. Reason being that it allows this method to be used in the same line as the constructor.
	 */
	public final CommandBase setCancelWhenReleased(Button button) {
		isWhileHeld = true;
		this.button = button;
		return this;
	}
	
	/**
	 * Completes and removes the current action running on the command.	
	 */
	public void progressActionSequence() {
		if(actionSeq.size() > actionPos) {
			Action action = actionSeq.get(actionPos);
			action.end();
			actionPos++;
		}
	}
	
	/**
	 * Adds a sequence of actions to the end of the current sequence.
	 * 
	 * @param actions The list of actions to be added.
	 */
	public void queueActions(Action... actions) {
		for(Action action : actions) actionSeq.add(action);
	}
}
