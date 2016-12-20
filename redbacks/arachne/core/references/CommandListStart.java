package redbacks.arachne.core.references;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.arachne.core.ArachneRobot;
import redbacks.arachne.core.SubsystemBase;

/**
 * CommandListStart should be the superclass for your CommandList class, which holds all of your commands.
 * 
 * @author Sean Zammit
 */
public class CommandListStart
{
	protected static SubsystemBase subsystemToUse;
	
	//Commands which require no subsystem.
	static {subsystemToUse = null;}
	public static CommandSetup
		doNothing = newCom(new AcDoNothing());

	//Example subsystem
	//static {subsystemToUse = exampleSubsystem;}
	//public static CommandSetup exampleCommand = newCom(new AcExample(), new AcExample());
			
	//Sequences
	static {subsystemToUse = ArachneRobot.sequencer;}
	//public static CommandSetup
	//	sequenceExample = newCom(
	//			new AcSeq.Sequential(exampleCommandA),
	//			new AcSeq.Parallel(exampleCommandB),
	//			new AcSeq.Parallel(exampleCommandC)
	//	);

	/**
	 * Creates a new command from a list of actions. The required subsystem will be the value of {@link #subsystemToUse subsystemToUse}.
	 * 
	 * @param actions The list of actions to be run in the command.
	 * @return The holder of the command. Use .c() to create a usable instance of the command.
	 */
	protected static CommandSetup newCom(Action... actions) {
		return new CommandSetup(subsystemToUse, actions);
	}
	
	/**
	 * Creates a new command from a list of actions and a subsystem.
	 * 
	 * @param actions The list of actions to be run in the command.
	 * @param subsystem The subsystem the command requires.
	 * @return The holder of the command. Use .c() to create a usable instance of the command.
	 */
	public static CommandSetup newCom(SubsystemBase subsystem, Action... actions) {
		return new CommandSetup(subsystem, actions);
	}
}
