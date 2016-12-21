package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.logic.GettableBoolean;

/**
 * A wrapper for any subclass of {@link Button} that is also a {@link GettableBoolean}.
 * This allows buttons to function like any other binary input.
 * It also extends Button so that it can be used in the place of the button it is wrapping.
 *
 * @author Sean Zammit
 */
public class ButtonGettableWrapper extends Button implements GettableBoolean
{
	/** The button being wrapped. */
	public Button button;

	/**
	 * Constructor for a wrapper that allows {@link Button Buttons} to be used as {@link GettableBoolean GettableBooleans}.
	 * 
	 * @param button
	 */
	private ButtonGettableWrapper(Button button) {
		this.button = button;
	}

	public boolean get() {
		return button.get();
	}

	/**
	 * Wraps a {@link Button} for use as a {@link GettableBoolean}.
	 * 
	 * @param button The button being wrapped.
	 * @return The wrapped button.
	 */
	public static ButtonGettableWrapper wrap(Button button) {
		return new ButtonGettableWrapper(button);
	}

	/**
	 * Wraps a list of {@link Button Buttons} for use as {@link GettableBoolean GettableBooleans}.
	 * 
	 * @param buttons The buttons being wrapped.
	 * @return The wrapped buttons.
	 */
	public static ButtonGettableWrapper[] wrapList(Button... buttons) {
		ButtonGettableWrapper[] newList = new ButtonGettableWrapper[buttons.length];
		for(int i = 0; i < buttons.length; i++)	newList[i] = new ButtonGettableWrapper(buttons[i]);
		return newList;
	}
}
