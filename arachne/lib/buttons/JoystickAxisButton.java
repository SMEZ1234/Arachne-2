package redbacks.arachne.lib.buttons;

import redbacks.arachne.core.CommandBase;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * An extension of the Button class that is triggered by a set value of an axis.
 * 
 * @author Sean Zammit
 */
public class JoystickAxisButton extends Button
{
    GenericHID m_joystick;
    int m_axis;
    boolean m_negative;
    double m_triggerPoint;

    /**
     * Creates a joystick axis 'button' for triggering commands.
     * 
     * @param joystick The controller which has the axis.
     * @param axis The axis on the controller.
     * @param negative Whether the trigger point is negative (top or left).
     * @param triggerPoint The value past which the button is triggered.
     */
    public JoystickAxisButton(GenericHID joystick, AxisType axis, boolean negative, double triggerPoint) {
        m_joystick = joystick;
        m_axis = axis.value;
        m_negative = negative;
        m_triggerPoint = Math.abs(triggerPoint);
    }

    public boolean get() {
        return CommandBase.driver.isDriverControl && (m_negative ? m_joystick.getRawAxis(m_axis) < -m_triggerPoint : m_joystick.getRawAxis(m_axis) > m_triggerPoint);
    }
}
