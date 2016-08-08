package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.logic.GettableBoolean;

public class ButtonWrapper implements GettableBoolean
{
	public Button button;
	
	private ButtonWrapper(Button button) {
		this.button = button;
	}
	
	public boolean get() {
		return button.get();
	}

	public static ButtonWrapper wrap(Button button) {
		return new ButtonWrapper(button);
	}

	public static ButtonWrapper[] wrapList(Button... buttons) {
		ButtonWrapper[] newList = new ButtonWrapper[buttons.length];
		for(int i = 0; i < buttons.length; i++) newList[i] = wrap(buttons[i]);
		return newList;
	}
}
