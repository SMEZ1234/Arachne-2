package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.logic.GettableNumber;

public class Timer implements GettableNumber
{
	double startTime;
	
	public Timer() {}
	
	public void start() {
		startTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
	}
	
	public double get() {
		return edu.wpi.first.wpilibj.Timer.getFPGATimestamp() - startTime;
	}
}
