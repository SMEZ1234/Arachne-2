package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.buttons.Button;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.logic.ListLogic;

/**
 * A button with multiple other buttons required to trigger it.
 * These buttons can be selectively used through a {@link ListLogic} operator.
 * 
 * @author Sean Zammit
 */
public class BtnMulti extends Button
{
	Button[] buttonList;

	ListLogic operator;

	/**
	 * Constructor for a button with multiple other buttons required to trigger it.
	 * 
	 * @param operator The {@link ListLogic} operator that determines the use of the list of buttons.
	 * @param buttons The list of buttons for the multi-button.
	 */
	public BtnMulti(ListLogic operator, Button... buttons) {
		this.operator = operator;
		if(buttons.length == 0) buttonList = new Button[] {new BtnCheck(new ChFalse())};
		else buttonList = buttons;
		this.operator.populateWorkingList(ButtonGettableWrapper.wrapList(buttonList));
	}

	public boolean get() {
		if(operator.get()) {
			operator.populateWorkingList(ButtonGettableWrapper.wrapList(buttonList));
			return true;
		}
		return false;
	}
}
