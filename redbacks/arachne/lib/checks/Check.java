package redbacks.arachne.lib.checks;

import edu.wpi.first.wpilibj.Timer;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * The main check class. Provides a base for all checks to be built on.
 * Do not create instances of this class.
 * 
 * It also holds the superclasses for the digital and analog checks.
 * 
 * @author Sean Zammit
 */
public abstract class Check implements GettableBoolean
{
	//JAVADOC Remember to mention null possibility.
	public CommandBase command;
	public Action action;
	
	private boolean isInverse = false;
	
	/** The time that this action began. Used for calculation of time spent running. */
	private double startTime = -1;
	
	public boolean isFinished() {
		return isDone() != isInverse;
	}
	
	public final Check not() {
		isInverse = !isInverse;
		return this;
	}
	
	/**
	 * Implementation of get() method from GettableBoolean interface.
	 */
	public boolean get() {
		return isFinished();
	}
	
	/**
	 * Performs the actual check.
	 * 
	 * @return Whether the check conditions have been fulfilled.
	 */
	protected abstract boolean isDone();

	/**
	 * A blank method that can be overwritten by individual checks. Runs every loop.
	 * This method will usually not be necessary.
	 */
	public void onRun() {}

	public void initialise(CommandBase command, Action action) {
	    startTime = Timer.getFPGATimestamp();
	    this.command = command;
	    this.action = action;
		onStart();
	}
	
	/**
	 * A blank method that can be overwritten by individual checks. Called once when the action begins.
	 * This method will usually not be necessary.
	 */
	public void onStart() {}

	/**
	 * A blank method that can be overwritten by individual checks. Called once when the action ends.
	 * This method will usually not be necessary.
	 */
	public void onFinish() {}
	
	//JAVADOC
	public final double timeSinceInitialized() {
	    return startTime < 0 ? 0 : Timer.getFPGATimestamp() - startTime;
	}
	
	/**
	 * The superclass for the digital checks. 
	 * It holds a variable specifying whether the value should be true or false.
	 */
	public static abstract class CheckDigital extends Check
	{
		/** Whether the value should be true. */
		public boolean type;
		
		/**
		 * @param isTriggered Whether the value should be true.
		 */
		public CheckDigital(boolean isTriggered) {
			type = isTriggered;
		}
	}
	
	/**
	 * The superclass for the analog checks. 
	 * It holds a variable specifying whether the value should be greater or less than the target, and a variable for the target.
	 */
	public static abstract class CheckAnalog extends Check
	{		
		/** The target value for the check. */
		public double value;
		
		/** Whether the returned value should be greater than the target. */
		public boolean type;
		
		/** Whether the absolute analog reading should be used. */
		public boolean isAbsolute;
		
		/** Whether whatever is returning an analog reading should be reset to 0 at the beginning of the check. */
		public boolean shouldReset;
		
		/**
		 * @param value The target value for the check.
		 * @param isGreaterThan Whether the returned value should be greater than the target.
		 * @param useAbsoluteReading Whether the absolute analog reading should be used.
		 * @param shouldReset Whether whatever is returning an analog reading should be reset to 0 at the beginning of the check.
		 */
		public CheckAnalog(double value, boolean isGreaterThan, boolean useAbsoluteReading, boolean shouldReset) {
			this.value = value;
			type = isGreaterThan;
			isAbsolute = useAbsoluteReading;
			this.shouldReset = shouldReset;
		}

		public boolean isDone() {
			return type ? (isAbsolute ? Math.abs(getAnalogValue()) : getAnalogValue()) > value : (isAbsolute ? Math.abs(getAnalogValue()) : getAnalogValue()) <= value;
		}
		
		public abstract double getAnalogValue();
	}
}
