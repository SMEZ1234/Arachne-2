package redbacks.arachne.lib.solenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * A wrapper for {@link SolDouble SolDoubles} to allow them to be treated as {@link GettableBoolean GettableBooleans}.
 *
 * @author Sean Zammit
 */
public class SolDoubleGettableWrapper implements GettableBoolean
{
	/** The double solenoid being wrapped. */
	public SolDouble solenoid;

	/**
	 * Constructor for a wrapper that allows {@link SolDouble SolDoubles} to be used as {@link GettableBoolean GettableBooleans}.
	 * 
	 * @param solenoid The double solenoid being wrapped.
	 */
	public SolDoubleGettableWrapper(SolDouble solenoid) {
		this.solenoid = solenoid;
	}

	public boolean get() {
		return solenoid.get() == Value.kForward;
	}
}
