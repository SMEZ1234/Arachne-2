package redbacks.arachne.lib.solenoids;

import edu.wpi.first.wpilibj.Solenoid;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * Functions as a single solenoid, but provides a workaround to get the state of the solenoid due to occasional feedback issues.
 * Is also a {@link GettableBoolean}.
 * 
 * @author Sean Zammit
 */
public class SolSingle extends Solenoid implements GettableBoolean
{
	boolean state = false;

	/**
	 * Constructor for a single solenoid.
	 * 
	 * @param port The port on the PCM where the solenoid is attached.
	 */
	public SolSingle(int port) {
		super(port);
	}

	public boolean get() {
		return state;
	}

	public void set(boolean on) {
		state = on;
		super.set(on);
	}
}
