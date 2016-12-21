package redbacks.arachne.lib.actions;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.commands.CommandBase;
import redbacks.arachne.lib.commands.CommandSetup;

/**
 * Holds all actions that run another sequence of actions.
 * This can be used for parallel sequences of actions running concurrently, or running a pre-existing command in the middle of an action sequence.
 * 
 * @author Sean Zammit
 */
public class AcSeq
{
	/**
	 * An action that runs a sequence of actions concurrently to the original sequence.
	 * 
	 * @author Sean Zammit
	 */
	public static class Parallel extends Action
	{
		/** The command holder from which the command will be created. */
		public CommandSetup sequence;

		/**
		 * Constructor for an action that runs a pre-existing command in parallel to the original sequence.
		 * 
		 * @param sequence A pre-existing command that will be triggered by this action.
		 */
		public Parallel(CommandSetup sequence) {
			super(new ChTrue());
			this.sequence = sequence;
		}

		/**
		 * Constructor for an action that runs a new list of actions in parallel to the original sequence, with a required subsystem.
		 * 
		 * @param requiredSystem The subsystem that this command requires, or null for none.
		 * @param actions The list of actions to be run in the command.
		 */
		public Parallel(SubsystemBase requiredSystem, Action... actions) {
			this(new CommandSetup(requiredSystem, actions));
		}

		/**
		 * Constructor for an action that runs a new list of actions in parallel to the original sequence, without a required subsystem.
		 * 
		 * @param actions The list of actions to be run in the command.
		 */
		public Parallel(Action... actions) {
			this(new CommandSetup(null, actions));
		}

		public void onFinish() {
			sequence.c().start();
		}
	}

	/**
	 * An action which runs a new sequence of actions.
	 * The original sequence will not progress until the new sequence is complete.
	 * 
	 * @author Sean Zammit
	 */
	public static class Sequential extends Action
	{
		/** The command holder from which the command will be created. */
		public final CommandSetup sequence;
		public CommandBase comToRun;
		public boolean hasStarted = false;

		/**
		 * Constructor for an action that runs a pre-existing command.
		 * 
		 * @param sequence A pre-existing command that will be triggered by this action.
		 */
		public Sequential(CommandSetup sequence) {
			super(new ChFalse());
			this.sequence = sequence;
		}

		/**
		 * Constructor for an action that runs a new list of actions, with a required subsystem.
		 * 
		 * @param requiredSystem The subsystem that this command requires, or null for none.
		 * @param actions The list of actions to be run in the command.
		 */
		public Sequential(SubsystemBase requiredSystem, Action... actions) {
			this(new CommandSetup(requiredSystem, actions));
		}

		/**
		 * Constructor for an action that runs a new list of actions, without a required subsystem.
		 * 
		 * @param actions The list of actions to be run in the command.
		 */
		public Sequential(Action... actions) {
			this(new CommandSetup(null, actions));
		}

		public void onStart() {
			comToRun = sequence.c();
			comToRun.start();
		}

		public void onRun() {
			if(!hasStarted && comToRun != null && comToRun.isRunning()) hasStarted = true;
		}

		public boolean isDone() {
			return hasStarted && comToRun != null && !comToRun.isRunning();
		}

		public void onFinish() {
			hasStarted = false;
		}
	}
}
