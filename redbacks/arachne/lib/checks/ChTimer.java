package redbacks.arachne.lib.checks;

/**
 * Uses the parent command's timer to run an action until a specified amount of time has passed.
 * 
 * @author Sean Zammit
 */
public class ChTimer extends Check
{
	double time;
	Timer timer;
	
	/**
	 * @param timeout The number of seconds until the check should be completed.
	 */
	public ChTimer(double timeout, Timer timer) {
		super();
		time = timeout;
		this.timer = timer;
	}

	public boolean isDone() {
		return time >= timer.get();
	}
}
