package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.logic.GettableBoolean;

public class ButtonGettableWrapper implements GettableBoolean
{
	public Button button;
	
	public ButtonGettableWrapper(Button button) {
		this.button = button;
	}
	
	public boolean get() {
		return button.get();
	}

	public static ButtonGettableWrapper[] wrapList(Button... buttons) {
		ButtonGettableWrapper[] newList = new ButtonGettableWrapper[buttons.length];
		for(int i = 0; i < buttons.length; i++) newList[i] = new ButtonGettableWrapper(buttons[i]);
		return newList;
	}
}
