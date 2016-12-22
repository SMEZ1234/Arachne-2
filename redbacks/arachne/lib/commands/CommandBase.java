package redbacks.arachne.lib.commands;

import java.util.ArrayList;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChQueue;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.motors.CtrlMotor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 * CommandBase is one of only two command classes that should exist using Arachne.
 * It holds a list of {@link Action Actions} that it will run in sequence. If you need a unique operation, you should instead create a new action.
 * Do not create instances of CommandBase yourself. Use an instance of {@link CommandSetup CommandSetup} instead.
 * 
 * @author Sean Zammit
 */
public class CommandBase extends Command
{
	/** The sequence of actions run by this command. */
	private final ArrayList<Action> actionSeq = new ArrayList<Action>();

	/** The position in the list of actions that the command is up to. */
	int actionPos;

	/** The list of motors that this command has set the speed of. Used by Arachne to disable them when the command ends. */
	public ArrayList<CtrlMotor> motorList = new ArrayList<CtrlMotor>();

	/** A boolean used to determine whether the command should stop running when the button to trigger it is released. */
	private boolean isWhileHeld = false;

	/** The button that, when released, will stop the command. It does not necessarily have to be the same button that activated it. */
	private Button button;

	/** The single subsystem required by this command. If necessary, individual actions can have their own subsystem requirements. */
	public SubsystemBase requiredSubsystem;

	/**
	 * Constructor for a command with a required subsystem and a list of actions to run.
	 * Do not call this. Use an instance of {@link CommandSetup CommandSetup} instead.
	 * 
	 * @param requiredSystem The subsystem that this command will run on, or null if none are required.
	 * @param actions The list of actions that are run in sequence by the command.
	 */
	protected CommandBase(SubsystemBase requiredSystem, Action... actions) {
		super();
		if(requiredSystem != null) requires(requiredSystem);
		if(actions.length > 0) for(Action action : actions) this.actionSeq.add(action);
		else this.actionSeq.add(new AcDoNothing(new ChTrue()));
	}

	/**
	 * Sets the required subsystem for this command.
	 * 
	 * @param subsystem The required subsystem.
	 */
	protected void requires(SubsystemBase subsystem) {
		super.requires(subsystem);
		requiredSubsystem = subsystem;
	}

	protected final void initialize() {
		actionPos = 0;
		actionSeq.get(0).initialise(this);
		if(requiredSubsystem != null && this != requiredSubsystem.getDefaultCommand()) requiredSubsystem.interruptRelatedSubsystems();
	}

	protected final void end() {
		for(CtrlMotor motor : motorList) if(motor.lastCommand == this && motor.shouldCancel) motor.disable();
		motorList.clear();
	}

	protected void interrupted() {
		this.end();
	}

	protected final boolean isFinished() {
		return (isWhileHeld && !button.get()) || actionSeq.size() <= actionPos;
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
	 * Sets the command to run only until a button is released.
	 * 
	 * @param button The button which will cancel the command when released.
	 * @return This command, so that you can call it on the constructor.
	 */
	public final CommandBase setCancelWhenReleased(Button button) {
		isWhileHeld = true;
		this.button = button;
		return this;
	}

	/**
	 * Completes the current action and progresses the sequence.
	 */
	public void progressActionSequence() {
		actionSeq.get(actionPos).end();
		actionPos++;
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
