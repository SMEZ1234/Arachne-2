package redbacks.arachne.lib.actions;

import java.util.function.BooleanSupplier;

import redbacks.arachne.lib.checks.Check;

/**
 * An action that can be fully defined by functional interfaces.
 * The intent of this class is to remove the requirement for teams to create a new action class for specialised one-off
 * actions like running an existing function or printing a reading. If you're using it for the same thing in multiple
 * places, it's probably better to just make a new class.
 * 
 * @author Sean Zammit
 */
public class AcLambda extends Action
{
	Runnable onStart, onRun, onFinish;
	BooleanSupplier isDone;
	
	/**
	 * Constructor for an action whose functionality is fully defined by functional interfaces.
	 * 
	 * @param check The condition that will finish the action.
	 * @param onRun A function to run repeatedly while the action runs. The function takes no input and returns no output.
	 */
	public AcLambda(Check check, Runnable onRun) {
		this(check, () -> {}, onRun, () -> {}, () -> false);
	}
	
	/**
	 * Constructor for an action whose functionality is fully defined by functional interfaces.
	 * 
	 * @param check The condition that will finish the action.
	 * @param onStart A function to run when the action starts. The function takes no input and returns no output.
	 * @param onRun A function to run repeatedly while the action runs. The function takes no input and returns no output.
	 * @param onFinish A function to run when the action finishes. The function takes no input and returns no output.
	 */
	public AcLambda(Check check, Runnable onStart, Runnable onRun, Runnable onFinish) {
		this(check, onStart, onRun, onFinish, () -> false);
	}
	
	/**
	 * Constructor for an action whose functionality is fully defined by functional interfaces.
	 * 
	 * @param check The condition that will finish the action.
	 * @param onStart A function to run when the action starts. The function takes no input and returns no output.
	 * @param onRun A function to run repeatedly while the action runs. The function takes no input and returns no output.
	 * @param onFinish A function to run when the action finishes. The function takes no input and returns no output.
	 * @param isDone A function to determine whether the action should stop running. The function takes no input and returns a boolean output.
	 */
	public AcLambda(Check check, Runnable onStart, Runnable onRun, Runnable onFinish, BooleanSupplier isDone) {
		super(check);
		this.onStart = onStart;
		this.onRun = onRun;
		this.onFinish = onFinish;
		this.isDone = isDone;
	}
	
	public void onStart() {
		onStart.run();
	}
	
	public void onRun() {
		onRun.run();
	}
	
	public void onFinish() {
		onFinish.run();
	}
	
	public boolean isDone() {
		return isDone.getAsBoolean();
	}
}
