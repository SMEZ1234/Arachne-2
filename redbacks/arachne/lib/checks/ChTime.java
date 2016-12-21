package redbacks.arachne.lib.checks;

/**
 * A check that will return true after a specified period of time.
 * 
 * @author Sean Zammit
 */
public class ChTime extends Check
{
	double time;
	
	/**
	 * Constructor for a check that will return true after a specified period of time.
	 * 
	 * @param timeout The number of seconds until the condition should be fulfilled.
	 */
	public ChTime(double timeout) {
		super();
		time = timeout;
	}

	public boolean isDone() {
		return timeSinceInitialized() >= time;
	}
}
