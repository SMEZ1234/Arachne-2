package redbacks.arachne.core;

import edu.wpi.first.wpilibj.buttons.*;
import redbacks.arachne.lib.commands.CommandBase;

/**
 * OIBase should be the superclass for your OI class, which maps operations to 'buttons'.
 *
 * @author Sean Zammit
 */
public abstract class OIBase
{
	/**
	 * Maps commands to buttons. This must be overridden by your OI class, and called from your {@link ArachneRobot#initDefaultCommands() Robot.initDefaultCommands()} method.
	 */
	public abstract void mapOperations();

	/**
	 * Sets the command to trigger once when the button is pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.arachne.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in your CommandList class.
	 */
	protected void whenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenPressed(command);
	}

	/**
	 * Sets the command to trigger once when the button is pressed. The command will automatically be cancelled if the button is released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.arachne.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in your CommandList class.
	 */
	protected void whenHeld(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenPressed(command.setCancelWhenReleased(button));
	}

	/**
	 * Sets the command to trigger once when the button is released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.arachne.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in your CommandList class.
	 */
	protected void whenReleased(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenReleased(command);
	}

	/**
	 * Sets the command to stop when the button is pressed.
	 * 
	 * @param button The button that should cancel the command. More complicated buttons can be found in 'redbacks.arachne.lib.input'.
	 * @param commands Any commands to stop. Commands should be set up in your CommandList class.
	 */
	protected void cancelWhenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.cancelWhenPressed(command);
	}

	/**
	 * Sets the command to toggle each time the button is pressed.
	 * 
	 * @param button The button that should toggle the command. More complicated buttons can be found in 'redbacks.arachne.lib.input'.
	 * @param commands Any commands to toggle. Commands should be set up in your CommandList class.
	 */
	protected void toggleWhenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.toggleWhenPressed(command);
	}

	/**
	 * Sets the command to repeatedly trigger while the button is pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.arachne.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in your CommandList class.
	 */
	protected void repeatWhileHeld(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whileHeld(command);
	}
}
