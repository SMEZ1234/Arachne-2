package redbacks.arachne.lib.actions.actuators;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.digital.ChBoolean;

/**
 * Action class used to set the value of a relay.
 * This action ends immediately, and sets the value of the relay on completion.
 * 
 * @author Sean Zammit
 */
public class AcRelay extends Action
{
	private Relay relay;
	private int on;
	
	/**
	 * @param relay The relay being set by this command.
	 * @param on Whether the relay should be set to be on.
	 */
	public AcRelay(Relay relay, boolean on) {
		super(new ChBoolean(true));
		this.relay = relay;
		this.on = on ? 1 : 0;
	}
	
	/**
	 * Creates a relay action that switches the current position of the relay.
	 * 
	 * @param relay The relay being set by this command.
	 */
	public AcRelay(Relay relay) {
		super(new ChBoolean(true));
		this.relay = relay;
		this.on = 2;
	}
	
	public void onFinish() {
		relay.set(
				on == 1 ? Value.kOn : 
				on == 0 ? Value.kOff : 
				
				relay.get() == Value.kOn ? Value.kOff : Value.kOn
		);
	}
}
