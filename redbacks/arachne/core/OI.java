package redbacks.arachne.core;

import redbacks.arachne.core.references.Autonomous;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * OI is used to map inputs to functions. An instance of OI can be found in CommandBase. DO NOT create one anywhere else.
 *
 * @author Sean Zammit
 */
public class OI
{
	/**
	 * Inputs are mapped to functions inside this constructor.
	 */
	public OI() {
		Autonomous.initAutonomous();
		
		//Set what commands will run when buttons are pressed/held/released here.
	}

	//Set up joysticks and buttons here.

	/**
	 * Triggers the command once when the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	@SuppressWarnings("unused")
	private void whenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenPressed(command);
	}

	/**
	 * Triggers the command once when the button is first pressed. The command will automatically be cancelled if the button is released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	@SuppressWarnings("unused")
	private void whenHeld(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenPressed(command.setCancelWhenReleased(button));
	}

	/**
	 * Triggers the command once when the button is first released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	@SuppressWarnings("unused")
	private void whenReleased(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whenReleased(command);
	}

	/**
	 * Cancels the command when the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to stop. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	@SuppressWarnings("unused")
	private void cancelWhenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.cancelWhenPressed(command);
	}

	/**
	 * Toggles the command each time the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	@SuppressWarnings("unused")
	private void toggleWhenPressed(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.toggleWhenPressed(command);
	}
	
	/**
	 * Triggers the command every loop while the button is pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.input'.
	 * @param commands Any commands to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	@SuppressWarnings("unused")
	private void pulseWhileHeld(Button button, CommandBase... commands) {
		for(CommandBase command : commands) button.whileHeld(command);
	}
}
