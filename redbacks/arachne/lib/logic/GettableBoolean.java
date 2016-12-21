package redbacks.arachne.lib.logic;

/**
 * An interface for anything that has a boolean output, so that it can be used in generic Arachne classes.
 *
 * @author Sean Zammit
 */
public interface GettableBoolean
{
	/**
	 * Used by GettableBoolean to return a boolean output.
	 * 
	 * @return A boolean output.
	 */
	public boolean get();
}
