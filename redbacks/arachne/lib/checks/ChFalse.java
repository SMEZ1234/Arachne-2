package redbacks.arachne.lib.checks;

/**
 * A check which always returns false.
 * 
 * @author Sean Zammit
 */
public class ChFalse extends Check
{
	/**
	 * Constructor for a check which always returns false.
	 */
	public ChFalse() {}
	
	public boolean isDone() {
		return false;
	}
}
