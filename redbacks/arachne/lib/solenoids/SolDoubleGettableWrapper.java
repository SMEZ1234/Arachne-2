package redbacks.arachne.lib.solenoids;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import redbacks.arachne.lib.logic.GettableBoolean;

public class SolDoubleGettableWrapper implements GettableBoolean
{
	public SolDouble solenoid;
	
	public SolDoubleGettableWrapper(SolDouble solenoid) {
		this.solenoid = solenoid;
	}
	
	public boolean get() {
		return solenoid.get() == Value.kForward;
	}
}
