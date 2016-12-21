package redbacks.arachne.lib.actions.actuators;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.ChTrue;

/**
 * An action used to set the value of a {@link Relay Relay}.
 * This action ends immediately, and sets the value of the relay on completion.
 * 
 * @author Sean Zammit
 */
public class AcRelay extends Action
{
	private Relay relay;
	private int on;

	/**
	 * Constructor for an action that will switch the current state of a Relay.
	 * 
	 * @param relay The relay being set by this action.
	 */
	public AcRelay(Relay relay) {
		super(new ChTrue());
		this.relay = relay;
		this.on = 2;
	}

	/**
	 * Constructor for an action that will set the state of a Relay to a specified state.
	 * 
	 * @param relay The relay being set by this action.
	 * @param on Whether the relay should be set to be on.
	 */
	public AcRelay(Relay relay, boolean on) {
		super(new ChTrue());
		this.relay = relay;
		this.on = on ? 1 : 0;
	}

	public void onFinish() {
		relay.set(on == 1 ? Value.kOn : on == 0 ? Value.kOff :
			relay.get() == Value.kOn ? Value.kOff : Value.kOn);
	}
}
