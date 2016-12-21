package redbacks.arachne.lib.checks;

import edu.wpi.first.wpilibj.Timer;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * The main check class. Provides a base for all checks to be built on.
 * 
 * It also holds the superclasses for digital and analog checks.
 * 
 * @author Sean Zammit
 */
public abstract class Check implements GettableBoolean
{
	/** The command within which the action that is running this check is running. Note that this may be null if the check is being used as a basic {@link GettableBoolean GettableBoolean}. */
	public CommandBase command;
	
	/** The action within which this check is running. Like {@link #command command}, this may be null if the check is being used as a basic {@link GettableBoolean GettableBoolean}. */
	public Action action;
	
	private boolean isInverse = false;
	
	/** The time that this check began. Used for calculation of time spent running. */
	private double startTime = -1;
	
	/**
	 * Inverts the condition of the check.
	 * 
	 * @return This check, so that you can call it on the constructor.
	 */
	public final Check not() {
		isInverse = !isInverse;
		return this;
	}
	
	/**
	 * Checks whether the condition of the check is fulfilled.
	 * This method also checks another method, {@link #isDone() isDone()}, which can be overwritten by individual checks.
	 * 
	 * @return Whether the condition of the check is fulfilled.
	 */
	public final boolean isFinished() {
		return isDone() != isInverse;
	}

	/**
	 * Runs when the check first starts. This method is used to handle anything that needs to be done before the check runs.
	 * This method also runs another method, {@link #onStart() onStart()}, which can be overwritten by individual checks.
	 * 
	 * @param command The command in which the action which is running this check is being run.
	 * @param action The action which is running this check.
	 */
	public final void initialise(CommandBase command, Action action) {
	    startTime = Timer.getFPGATimestamp();
	    this.command = command;
	    this.action = action;
		onStart();
	}
	
	/**
	 * Implementation of get() method from GettableBoolean interface.
	 */
	public boolean get() {
		return isFinished();
	}
	
	/**
	 * Used by individual checks to set the conditions that must be fulfilled.
	 * 
	 * @return Whether the check conditions have been fulfilled.
	 */
	protected abstract boolean isDone();

	/**
	 * Used by individual checks to perform any operations while the check is running.
	 * This method will usually not be necessary.
	 */
	public void onRun() {}
	
	/**
	 * Used by individual checks to perform any operations when the check first begins to run.
	 * This method will usually not be necessary.
	 */
	public void onStart() {}

	/**
	 * Used by individual checks to perform any operations when the check is complete.
	 * This method will usually not be necessary.
	 */
	public void onFinish() {}
	
	/**
	 * Returns the time since the check was initialised, in seconds.
	 * 
	 * @return The time since the check was initialised, in seconds.
	 */
	public final double timeSinceInitialized() {
	    return startTime < 0 ? 0 : Timer.getFPGATimestamp() - startTime;
	}
	
	/**
	 * The superclass for the digital checks.
	 * 
	 * @author Sean Zammit
	 */
	public static abstract class CheckDigital extends Check
	{
		/** Whether the value should be true. */
		public boolean type;
		
		/**
		 * Super constructor for digital checks.
		 * 
		 * @param isTriggered Whether the value should be true.
		 */
		public CheckDigital(boolean isTriggered) {
			type = isTriggered;
		}
	}
	
	/**
	 * The superclass for the analog checks.
	 * 
	 * @author Sean Zammit
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
		 * Super constructor for analog checks.
		 * 
		 * @param value The target analog reading for the check.
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
		
		/**
		 * Used by individual analog checks to specify the value which will be used to determine whether the check's condition has been met.
		 * 
		 * @return The analog reading that will be used.
		 */
		public abstract double getAnalogValue();
	}
}
