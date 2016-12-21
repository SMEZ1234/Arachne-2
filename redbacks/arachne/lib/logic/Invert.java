package redbacks.arachne.lib.logic;

/**
 * A {@link GettableBoolean} that inverts the output from another GettableBoolean.
 *
 * @author Sean Zammit
 */
public class Invert implements GettableBoolean
{
	/** The gettable being inverted. */
	public GettableBoolean parentGettable;

	/**
	 * Constructor for a {@link GettableBoolean} that inverts the output from another GettableBoolean.
	 * 
	 * @param gettable The gettable being inverted.
	 */
	private Invert(GettableBoolean gettable) {
		this.parentGettable = gettable;
	}

	/**
	 * Creates a {@link GettableBoolean} that inverts the output from another GettableBoolean.
	 * 
	 * @param gettable The gettable being inverted.
	 * @return The new GettableBoolean
	 */
	public static Invert invert(GettableBoolean gettable) {
		return new Invert(gettable);
	}

	public boolean get() {
		return !parentGettable.get();
	}
}
