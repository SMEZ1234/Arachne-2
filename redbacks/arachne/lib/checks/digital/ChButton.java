package redbacks.arachne.lib.checks.digital;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.checks.Check.CheckDigital;

/**
 * Checks the value of a button.
 * 
 * @author Sean Zammit
 */
public class ChButton extends CheckDigital {
	Button button;
	
	/**
	 * @param button The button that is being checked.
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