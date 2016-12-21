package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A button that is triggered when a joystick POV is at a certain angle.
 * 
 * @author Sean Zammit
 */
public class BtnPOV extends Button
{
	GenericHID m_joystick;
	int m_angle;

	/**
	 * Constructor for a button that is triggered when a joystick POV is at a certain angle.
	 * 
	 * @param joystick The controller which has the POV.
	 * @param angle The required angle for the POV.
	 */
	public BtnPOV(GenericHID joystick, int angle) {
		m_joystick = joystick;
		m_angle = angle;
	}

	public boolean get() {
		return m_joystick.getPOV() == m_angle;
	}
}
