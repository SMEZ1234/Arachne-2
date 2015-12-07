package redbacks.arachne.core;

import redbacks.arachne.core.references.Autonomous;
import redbacks.arachne.lib.buttons.JoystickPOVButton;
import redbacks.arachne.lib.commands.CommandRB;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import static redbacks.arachne.core.CommandBase.*;

/**
 * OI is used to map inputs to functions. An instance of OI can be found in CommandBase. DO NOT create one anywhere else.
 *
 * @author Sean Zammit
 */
@SuppressWarnings("unused")
public class OI
{
	/**
	 * Inputs are mapped to functions inside this constructor.
	 */
	public OI() {
		Autonomous.initAutonomous();
		
		//Set what commands will run when buttons are pressed/held/released here.
		whenPressed(d_LStick, driver.cShiftFast.c());
		whenPressed(d_RStick, driver.cShiftSlow.c());
		whenPressed(d_B, driver.cShift.c());
	}

	//Set up joysticks and buttons here.
	public static final Joystick stickDriver = new Joystick(0);
	public static final Button 
		d_X = new JoystickButton(stickDriver, 1),
		d_A = new JoystickButton(stickDriver, 2),
		d_B = new JoystickButton(stickDriver, 3),
		d_Y = new JoystickButton(stickDriver, 4),
		d_LB = new JoystickButton(stickDriver, 5),
		d_RB = new JoystickButton(stickDriver, 6),
		d_LT = new JoystickButton(stickDriver, 7),
		d_RT = new JoystickButton(stickDriver, 8),
		d_Back = new JoystickButton(stickDriver, 9),
		d_Start = new JoystickButton(stickDriver, 10),
		d_LStick = new JoystickButton(stickDriver, 11),
		d_RStick = new JoystickButton(stickDriver, 12),

		d_POV_U = new JoystickPOVButton(stickDriver, 0),
		d_POV_R = new JoystickPOVButton(stickDriver, 90),
		d_POV_D = new JoystickPOVButton(stickDriver, 180),
		d_POV_L = new JoystickPOVButton(stickDriver, 270);
	
	public static final Joystick stickFunction = new Joystick(1);
	public static final Button 
		f_X = new JoystickButton(stickFunction, 1),
		f_A = new JoystickButton(stickFunction, 2),
		f_B = new JoystickButton(stickFunction, 3),
		f_Y = new JoystickButton(stickFunction, 4),
		f_LB = new JoystickButton(stickFunction, 5),
		f_RB = new JoystickButton(stickFunction, 6),
		f_LT = new JoystickButton(stickFunction, 7),
		f_RT = new JoystickButton(stickFunction, 8),
		f_Back = new JoystickButton(stickFunction, 9),
		f_Start = new JoystickButton(stickFunction, 10),
		f_LStick = new JoystickButton(stickFunction, 11),
		f_RStick = new JoystickButton(stickFunction, 12),

		f_POV_U = new JoystickPOVButton(stickFunction, 0),
		f_POV_R = new JoystickPOVButton(stickFunction, 90),
		f_POV_D = new JoystickPOVButton(stickFunction, 180),
		f_POV_L = new JoystickPOVButton(stickFunction, 270);

	/**
	 * Triggers the command once when the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.buttons'.
	 * @param command The command to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	private void whenPressed(Button button, CommandRB command) {
		button.whenPressed(command);
	}

	/**
	 * Triggers the command once when the button is first pressed. The command will automatically be cancelled if the button is released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.buttons'.
	 * @param command The command to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	private void whenHeld(Button button, CommandRB command) {
		button.whenPressed(command.setCancelWhenReleased(button));
	}

	/**
	 * Triggers the command once when the button is first released.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.buttons'.
	 * @param command The command to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	private void whenReleased(Button button, CommandRB command) {
		button.whenReleased(command);
	}

	/**
	 * Cancels the command when the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.buttons'.
	 * @param command The command to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	private void cancelWhenPressed(Button button, CommandRB command) {
		button.cancelWhenPressed(command);
	}

	/**
	 * Toggles the command each time the button is first pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.buttons'.
	 * @param command The command to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	private void toggleWhenPressed(Button button, CommandRB command) {
		button.toggleWhenPressed(command);
	}
	
	/**
	 * Triggers the command every loop while the button is pressed.
	 * 
	 * @param button The button that should trigger the command. More complicated buttons can be found in 'redbacks.lib.buttons'.
	 * @param command The command to run. Commands should be set up in the file of the required subsystem, or CommandList should they not require a subsystem.
	 */
	private void pulseWhileHeld(Button button, CommandRB command) {
		button.whileHeld(command);
	}
}
