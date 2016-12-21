package redbacks.arachne.lib.checks;

/**
 * A check which always returns true.
 * 
 * @author Sean Zammit
 */
public class ChTrue extends Check
{
	/**
	 * Constructor for a check which always returns true.
	 */
	public ChTrue() {}

	public boolean isDone() {
		return true;
	}
}
