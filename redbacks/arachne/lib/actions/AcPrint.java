package redbacks.arachne.lib.actions;

import redbacks.arachne.lib.checks.ChTrue;

/**
 * An action that exists to print debugging messages. Ends instantly and prints the string it was given.
 * 
 * @author Sean Zammit
 */
public class AcPrint extends Action
{
	/** The string that the action will print when it ends */	
	String stringToPrint;
	
	/**
	 * Constructor creating an action to print a specified string.
	 * 
	 * @param string The string to print.
	 */
	public AcPrint(String string) {
		super(new ChTrue());
		stringToPrint = string;
	}
	
	protected void onFinish() {
		System.out.println(stringToPrint);
	}
}
