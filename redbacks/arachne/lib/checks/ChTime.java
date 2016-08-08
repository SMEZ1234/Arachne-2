package redbacks.arachne.lib.checks;

/**
 * Uses the timer to run an action until a specified amount of time has passed.
 * 
 * @author Sean Zammit
 */
public class ChTime extends Check
{
	double time;
	
	/**
	 * @param timeout The number of seconds until the check should be completed.
	 */
	public ChTime(double timeout) {
		super();
		time = timeout;
	}

	public boolean isDone() {
		return timeSinceInitialized() >= time;
	}
}
