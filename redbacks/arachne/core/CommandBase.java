package redbacks.arachne.core;

import java.util.ArrayList;

import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.digital.ChBoolean;
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
	/** The instance of the operator interface. This is used to map inputs to functions. */
	public static OI oi;

	/**
	 * A full list of subsystems on the robot.
	 * Arachne only uses this list when interrupting all subsystems on the robot, but it's able to be used for other purposes as well, such as determining which subsystems are currently busy, etc.
	 */
	public static ArrayList<SubsystemBase> subsystemList = new ArrayList<SubsystemBase>();

	//Create an instance of each subsystem here.
	//public static SubsystemExample exampleSubsystem = new SubsystemExample();
	
	/** Exists so that complicated sequences involving many systems on the robot can be interrupted by another sequence. */
	public static SubsystemBase sequencer = new SubsystemBase();
	
	/**
	 * Called when the robot is first initialised.
	 * In this case, it sets up the subsystems and the instance of the OI.
	 */
	public static void init() {
		//Set default commands for each subsystem here.
		//exampleSubsystem.setDefaultCommand(CommandList.exampleCommand.c());
		
		//Don't move or change this. EVER. Without it, no operator interface, no mapping, therefore "robot no move."
		oi = new OI();
	}
	
	//Arachne's additions follow.
	//Don't modify any code below this point unless you're sure you know what you're doing.
	
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
		else this.actionSeq.add(new AcDoNothing(new ChBoolean(true)));
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
		Action action = readAction();
		if(action != null) action.periodic();
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
	
	/**
	 * Handles the sequence of actions in the command.
	 * 
	 * @return The current action. Moves on to the next if the action is complete.
	 */
	private final Action readAction() {
		if(actionSeq.size() > actionPos) {
			Action action = actionSeq.get(actionPos);
			
			if(action.check instanceof ChBoolean) {
				if(((ChBoolean) action.check).type == true) {
					action.initialise(this);
					action.periodic();
					action.end();
					actionPos++;
					if(actionSeq.size() > actionPos) actionSeq.get(actionPos).initialise(this);
				}
				else if(actionSeq.size() > actionPos + 1) {
					action.end();
					actionPos++;
					actionSeq.get(actionPos).initialise(this);
				}
			}
			else if(action.isFinished()) {
				action.end();
				if(action.isInterrupted) this.cancel();
				else {
					actionPos++;
					if(actionSeq.size() > actionPos) actionSeq.get(actionPos).initialise(this);
				}
			}
			if(actionSeq.size() > actionPos) return actionSeq.get(actionPos);
		}
		return null;
	}
}
