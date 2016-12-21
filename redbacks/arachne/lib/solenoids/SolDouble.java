package redbacks.arachne.lib.solenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Functions as a double solenoid, but provides a workaround to get the state of the solenoid due to occasional feedback issues.
 * 
 * @author Sean Zammit
 */
public class SolDouble extends DoubleSolenoid
{
	Value state = Value.kReverse;

	/**
	 * Constructor for a double solenoid.
	 * 
	 * @param portForward The forward port on the PCM where the solenoid is attached.
	 * @param portReverse The reverse port on the PCM where the solenoid is attached.
	 */
	public SolDouble(int portForward, int portReverse) {
		super(portForward, portReverse);
	}

	/**
	 * Returns the last non-off value of the solenoid.
	 * 
	 * @return The value of the solenoid.
	 */
	public Value get() {
		return state;
	}

	public void set(Value value) {
		if(value != Value.kOff) state = value;
		super.set(value);
	}
}