package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.logic.ListLogic;

/**
 * An extension of the Button class that allows for a list of buttons to be required to trigger the action.
 * 
 * @author Sean Zammit
 */
public class BtnList extends Button
{
	Button[] buttonList;

	ListLogic operator;
	
	/**
	 * Creates a 'button' holding a list of buttons that must all be pressed to trigger a command.
	 * 
	 * @param buttons
	 * The list of buttons.
	 */
	protected BtnList(ListLogic operator, Button... buttons) {
		this.operator = operator;
		buttonList = buttons;
		this.operator.populateWorkingList(ButtonWrapper.wrapList(buttonList));
	}

	public boolean get() {
		if(operator.get()) {
			operator.populateWorkingList(ButtonWrapper.wrapList(buttonList));
			return true;
		}
		return false;
	}
}
