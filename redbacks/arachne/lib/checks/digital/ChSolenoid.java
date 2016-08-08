package redbacks.arachne.lib.checks.digital;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import redbacks.arachne.lib.checks.Check.CheckDigital;
import redbacks.arachne.lib.solenoids.SolDouble;
import redbacks.arachne.lib.solenoids.SolSingle;

/**
 * Holds the solenoid position checks.
 * 
 * @author Sean Zammit
 */
public class ChSolenoid
{
	/**
	 * The check for a single solenoid.
	 * Uses a variable in SolSingle to get around the normal bugs when checking solenoid position.
	 */
	public static class Single extends CheckDigital
	{
		SolSingle solenoid;

		/**
		 * @param solenoid The solenoid being checked.
		 * @param isOn Whether the check is for it being on.
		 */
		public Single(SolSingle solenoid, boolean isOn) {
			super(isOn);
			this.solenoid = solenoid;
		}

		public boolean isDone() {
			return solenoid.get() == type;
		}
	}

	/**
	 * The check for a double solenoid.
	 * Uses a variable in SolDouble to get around the normal bugs when checking solenoid position.
	 */
	public static class Double extends CheckDigital
	{
		SolDouble solenoid;

		/**
		 * @param solenoid The double solenoid being checked.
		 * @param isOn Whether the check is for it being on.
		 */
		public Double(SolDouble solenoid, boolean isOn) {
			super(isOn);
			this.solenoid = solenoid;
		}

		public boolean isDone() {
			return (solenoid.get() == Value.kForward) == type;
		}
	}
}