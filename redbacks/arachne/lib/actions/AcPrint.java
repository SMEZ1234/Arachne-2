package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.digital.ChBoolean;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcPrint extends Action
{
	String stringToPrint;
	
	public AcPrint(String string) {
		super(new ChBoolean(true));
		stringToPrint = string;
	}
	
	protected void onFinish() {
		System.out.println(stringToPrint);
	}
	
	public boolean isDone() {
		return true;
	}
}
