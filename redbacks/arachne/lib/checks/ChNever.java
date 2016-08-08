package redbacks.arachne.lib.checks;

/**
 * Functions the same as ChBoolean(false), except that commands will never skip over this check when there is another action waiting.
 * 
 * @author Sean Zammit
 */
public class ChNever extends Check
{
	public ChNever() {}
	
	public boolean isDone() {
		return false;
	}
}
