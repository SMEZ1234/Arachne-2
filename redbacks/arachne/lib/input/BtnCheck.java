package redbacks.arachne.lib.input;

import redbacks.arachne.lib.checks.Check;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A button that is triggered when a {@link Check} condition is met.
 * 
 * @author Sean Zammit
 */
public class BtnCheck extends Button
{
	Check check;
	boolean isStarted = false;
	boolean resetCheck = true;

	/**
	 * Constructor for a button that is triggered when a {@link Check} condition is met.
	 * 
	 * @param check The check whose condition must be met.
	 */
	public BtnCheck(Check check) {
		this.check = check;
	}

	/**
	 * Constructor for a button that is triggered when a {@link Check} condition is met.
	 * 
	 * @param check The check whose condition must be met.
	 * @param resetCheckOnCompletion Whether the check should be completed and restarted each time its condition is met.
	 */
	public BtnCheck(Check check, boolean resetCheckOnCompletion) {
		this(check);
		this.resetCheck = resetCheckOnCompletion;
	}

	public boolean get() {
		if(!isStarted) check.initialise(null, null);
		isStarted = true;
		check.onRun();
		if(check.isFinished()) {
			if(resetCheck) {
				check.onFinish();
				isStarted = false;
			}
			return true;
		}
		return false;
	}
}
