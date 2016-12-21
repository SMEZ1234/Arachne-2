package redbacks.arachne.lib.input;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A button that is triggered when a {@link JoystickAxis} passes a specific point.
 * 
 * @author Sean Zammit
 */
public class BtnAxis extends Button
{
	JoystickAxis m_axis;
	boolean m_negative;
	double m_triggerPoint;

	/**
	 * Constructor for a button that is triggered when a {@link JoystickAxis} passes a specific point.
	 * 
	 * @param axis The JoystickAxis.
	 * @param negative Whether the trigger point is negative.
	 * @param triggerPoint The value past which the button is triggered.
	 */
	public BtnAxis(JoystickAxis axis, boolean negative, double triggerPoint) {
		m_axis = axis;
		m_negative = negative;
		m_triggerPoint = Math.abs(triggerPoint);
	}

	public boolean get() {
		return m_negative ? m_axis.get() < -m_triggerPoint : m_axis.get() > m_triggerPoint;
	}
}
