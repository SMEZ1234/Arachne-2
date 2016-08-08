package redbacks.arachne.core.references;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.arachne.lib.subsystems.SubsystemBase;
import static redbacks.arachne.core.CommandBase.*;

/**
 * Holds all commands.
 * 
 * @author Sean Zammit
 */
public class CommandList
{
	private static SubsystemBase subsystemToUse;
	
	//Commands which require no subsystem.
	public static CommandSetup
		doNothing = newCom(new AcDoNothing());

	//Example subsystem
	//static SwitchSubsystem s_exampleSubsystem = new SwitchSubsystem(exampleSubsystem);
	//public static CommandSetup exampleCommand = newCom(new ActionExample(), new ActionExample());
			
	//Sequences
	static SwitchSubsystem s_sequencer = new SwitchSubsystem(sequencer);
	//public static CommandSetup
	//	sequenceExample = newCom(
	//			new AcSeq.Sequential(exampleCommandA),
	//			new AcSeq.Parallel(exampleCommandB),
	//			new AcSeq.Parallel(exampleCommandC)
	//	);

	/**
	 * Creates a new command from a list of actions.
	 * 
	 * @param actions The list of actions to be run in the command.
	 * @return The holder of the command. Use .c() to access the command.
	 */
	private static CommandSetup newCom(Action... actions) {
		return new CommandSetup(subsystemToUse, actions);
	}
	
	/**
	 * Creates a new command from a list of actions and a subsystem.
	 * 
	 * @param actions The list of actions to be run in the command.
	 * @param subsystem The subsystem the command requires.
	 * @return The holder of the command. Use .c() to access the command.
	 */
	public static CommandSetup newCom(SubsystemBase subsystem, Action... actions) {
		return new CommandSetup(subsystem, actions);
	}
	
	public static class SwitchSubsystem
	{
		public SwitchSubsystem(SubsystemBase subsystem) {
			subsystemToUse = subsystem;
		}
	}
}
