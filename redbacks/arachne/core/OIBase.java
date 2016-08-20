package redbacks.arachne.core;

import edu.wpi.first.wpilibj.buttons.*;
import redbacks.arachne.lib.commands.CommandBase;

/**
 * OI is used to map inputs to functions. An instance of OI can be found in CommandBase. DO NOT create one anywhere else.
 *
 * @author Sean Zammit
 */
public abstract class OIBase
{
	public abstract void mapOperations();
	
	/**
	 * Triggers the command once when the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandListStart should they not require a subsystem.
	 */
	protected void whenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenPressed(command);
	}

	/**
	 * Triggers the command once when the button is first pressed. The command will automatically be cancelled if the button is released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandListStart should they not require a subsystem.
	 */
	protected void whenHeld(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenPressed(command.setCancelWhenReleased(button));
	}

	/**
	 * Triggers the command once when the button is first released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandListStart should they not require a subsystem.
	 */
	protected void whenReleased(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenReleased(command);
	}

	/**
	 * Cancels the command when the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to stop. Commands should be set up in the file of the required subsystem, or CommandListStart should they not require a subsystem.
	 */
	protected void cancelWhenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.cancelWhenPressed(command);
	}

	/**
	 * Toggles the command each time the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandListStart should they not require a subsystem.
	 */
	protected void toggleWhenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.toggleWhenPressed(command);
	}
	
	/**
	 * Triggers the command every loop while the button is pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandListStart should they not require a subsystem.
	 */
	protected void pulseWhileHeld(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whileHeld(command);
	}
}
