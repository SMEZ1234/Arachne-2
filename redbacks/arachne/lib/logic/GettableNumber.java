package redbacks.arachne.lib.logic;

/**
 * An interface for anything that has a numeric output, so that it can be used in generic Arachne classes.
 *
 * @author Sean Zammit
 */
public interface GettableNumber
{
	/**
	 * Used by GettableNumber to return a numeric output.
	 * 
	 * @return A numeric output.
	 */
	public double get();
}
