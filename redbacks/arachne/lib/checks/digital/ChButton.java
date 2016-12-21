package redbacks.arachne.lib.checks.digital;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.checks.Check.CheckDigital;

/**
 * Checks the value of a button.
 * 
 * @author Sean Zammit
 */
public class ChButton extends CheckDigital
{
	Button button;

	/**
	 * Constructor for a check that will return true based on the state of a button.
	 * 
	 * @param button The button being checked.
	 * @param isTriggered Whether the value should be true.
	 */
	public ChButton(Button button, boolean isTriggered) {
		super(isTriggered);
		this.button = button;
	}

	public boolean isDone() {
		return button.get() == type;
	}
}