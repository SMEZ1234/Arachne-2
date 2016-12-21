package redbacks.arachne.lib.actions.actuators;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTime;
import redbacks.arachne.lib.checks.ChTrue;

/**
 * Holds all solenoid actions.
 * 
 * @author Sean Zammit
 */
public class AcSolenoid
{
	/**
	 * An action used to set the value of a single {@link Solenoid Solenoid}.
	 * This action ends immediately, and sets the value of the solenoid on completion.
	 * 
	 * @author Sean Zammit
	 */
	public static class Single extends Action
	{
		private Solenoid solenoid;
		private boolean position;

		/**
		 * Constructor for an action that will set the position of a {@link Solenoid Solenoid} to a specified position.
		 * 
		 * @param solenoid The single Solenoid being set.
		 * @param position The position the solenoid should be set to.
		 */
		public Single(Solenoid solenoid, boolean position) {
			super(new ChTrue());
			this.solenoid = solenoid;
			this.position = position;
		}

		public void onFinish() {
			solenoid.set(position);
		}
	}

	/**
	 * An action used to set the value of a {@link DoubleSolenoid DoubleSolenoid}.
	 * This action takes 0.1 seconds to complete. It sets the value of the solenoid at the beginning of the action, and turns it off at the end.
	 * 
	 * @author Sean Zammit
	 */
	public static class Double extends Action
	{
		private DoubleSolenoid solenoid;
		private Value position;

		/**
		 * Constructor for an action that will set the position of a {@link DoubleSolenoid DoubleSolenoid} to a specified position.
		 * 
		 * @param solenoid The DoubleSolenoid being set.
		 * @param position The position as a {@link Value DoubleSolenoid.Value} that this solenoid should be set to.
		 */
		public Double(DoubleSolenoid solenoid, Value position) {
			super(new ChTime(0.1D));
			this.solenoid = solenoid;
			this.position = position;
		}

		public void onStart() {
			solenoid.set(position);
		}

		public void onFinish() {
			solenoid.set(Value.kOff);
		}
	}
}
