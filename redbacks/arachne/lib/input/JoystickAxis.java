package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.Joystick;
import redbacks.arachne.lib.logic.GettableNumber;

/**
 * An axis on a joystick, for use in creating buttons (see {@link BtnAxis}) or as a {@link GettableNumber}.
 *
 * @author Sean Zammit
 */
public class JoystickAxis implements GettableNumber
{
	/** The joystick that this axis is on. */
	public Joystick joystick;

	/** The ID of the axis on the joystick. */
	public int axis;

	/**
	 * Constructor for an instance of a joystick axis.
	 * 
	 * @param stick The joystick that this axis is on.
	 * @param axisID The ID of the axis on the joystick.
	 */
	public JoystickAxis(Joystick stick, int axisID) {
		joystick = stick;
		axis = axisID;
	}

	public double get() {
		return joystick.getRawAxis(axis);
	}
}
