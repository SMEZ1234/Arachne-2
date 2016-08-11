package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTrue;

/**
 * JAVADOC
 * 
 * @author Sean Zammit
 */
public class AcPrint extends Action
{
	String stringToPrint;
	
	public AcPrint(String string) {
		super(new ChTrue());
		stringToPrint = string;
	}
	
	protected void onFinish() {
		System.out.println(stringToPrint);
	}
	
	public boolean isDone() {
		return true;
	}
}
