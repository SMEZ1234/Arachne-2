package redbacks.arachne.lib.checks;

/**
 * A check which always returns false.
 * 
 * @author Sean Zammit
 */
public class ChFalse extends Check
{
	public boolean isDone() {
		return false;
	}
}
