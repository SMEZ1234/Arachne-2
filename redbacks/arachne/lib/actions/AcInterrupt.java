package redbacks.arachne.lib.actions;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.checks.digital.ChBoolean;
import redbacks.arachne.lib.subsystems.SubsystemBase;

/**
 * Holds all actions that interrupt other functions.
 * 
 * @author Sean Zammit
 */
public class AcInterrupt
{
	/**
	 * Cancels the operation of a single action in a specified command.
	 */
	public static class KillAction extends Action
	{
		CommandBase target;

		/**
		 * @param commandToProgress The command which will be manually progressed to the next action.
		 */
		public KillAction(CommandBase commandToProgress) {
			super(new ChBoolean(true));
			target = commandToProgress;
		}

		public void onFinish() {
			target.progressActionSequence();
		}
	}

	/**
	 * Cancels the operation of a specified command.
	 */
	public static class KillCommand extends Action
	{
		CommandBase target;

		/**
		 * @param commandToKill The command which will be cancelled.
		 */
		public KillCommand(CommandBase commandToKill) {
			super(new ChBoolean(true));
			target = commandToKill;
		}

		public void onFinish() {
			target.cancel();
		}
	}

	/**
	 * Cancels the operation of a specified command.
	 */
	public static class KillAllCommands extends Action
	{
		/**
		 * @param commandToKill The command which will be cancelled.
		 */
		public KillAllCommands() {
			super(new ChBoolean(true));
		}

		public void onFinish() {
			Scheduler.getInstance().removeAll();
		}
	}

	/**
	 * Cancels the operation of whatever command is running on a specified subsystem.
	 */
	public static class KillSubsystem extends Action
	{
		SubsystemBase target;

		/**
		 * @param subsystemToReset The subsystem to cancel the current command on.
		 */
		public KillSubsystem(SubsystemBase subsystemToReset) {
			super(new ChBoolean(true));
			target = subsystemToReset;
		}

		public void onFinish() {
			if(target.getCurrentCommand() != null) target.getCurrentCommand().cancel();
		}
	}

	/**
	 * Cancels the operation of all commands that have a dedicated subsystem.
	 */
	public static class KillAllSubsystems extends Action
	{
		public KillAllSubsystems() {
			super(new ChBoolean(true));
		}

		public void onFinish() {
			for(Subsystem subsystem : CommandBase.subsystemList)
				if(subsystem.getCurrentCommand() != null) subsystem.getCurrentCommand().cancel();
		}
	}
}
