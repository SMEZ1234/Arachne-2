package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.Timer;
import redbacks.arachne.lib.checks.digital.ChBoolean;

/**
 * @author Sean Zammit
 */
public class ActionStartTimer extends Action
{
	Timer timer;
	
	public ActionStartTimer(Timer timer) {
		super(new ChBoolean(true));
		this.timer = timer;
	}
	
	protected void onFinish() {
		timer.start();
	}
}
