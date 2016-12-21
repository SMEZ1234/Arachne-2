package redbacks.arachne.lib.checks;

/**
 * A check which always returns true.
 * 
 * @author Sean Zammit
 */
public class ChTrue extends Check
{
	public boolean isDone() {
		return true;
	}
}
