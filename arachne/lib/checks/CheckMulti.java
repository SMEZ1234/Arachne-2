package redbacks.arachne.lib.checks;

import redbacks.arachne.lib.commands.CommandRB;

/**
 * Holds all the checks that have multiple conditions.
 * Also functions as their superclass.
 * 
 * @author Sean Zammit
 */
public abstract class CheckMulti extends Check
{
	/** The list of subchecks for the main check. */
	public Check[] checklist;
	
	/**
	 * @param checks The list of subchecks for the main check.
	 */
	public CheckMulti(Check... checks) {
		if(checks.length == 0) checklist = new Check[] {
			new Check() {
				public boolean isTrue(CommandRB command) {
					return false;
				}
			}
		};
		else checklist = checks;
	}
	
	/**
	 * Requires all subchecks to be true.
	 */
	public static class And extends CheckMulti
	{
		public boolean isTrue(CommandRB command) {
			for(int i = 0; i < checklist.length; i++) {
				if(!checklist[i].isTrue(command)) return false; 
			}
			return true; 
		}
	}

	/**
	 * Requires any subcheck to be true.
	 */
	public static class Or extends CheckMulti
	{
		public boolean isTrue(CommandRB command) {
			for(int i = 0; i < checklist.length; i++) {
				if(checklist[i].isTrue(command)) return true; 
			}
			return false; 
		}
	}

	/**
	 * Requires one and only one subcheck to be true.
	 */
	public static class Xor extends CheckMulti
	{
		public boolean isTrue(CommandRB command) {
			for(int i = 0; i < checklist.length; i++) {
				if(checklist[i].isTrue(command)) {
					for(int j = 0; j < checklist.length; j++) {
						if(i != j && checklist[i].isTrue(command)) return false;
					}
					return true;
				}
			}
			return false; 
		}
	}
}
