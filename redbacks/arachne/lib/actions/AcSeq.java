package redbacks.arachne.lib.actions;

import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.commands.CommandSetup;
import redbacks.arachne.lib.subsystems.SubsystemBase;

/**
 * This Action will run a command populated with a list of provided actions, or a pre-existing command.
 * 
 * @author Sean Zammit
 */
public class AcSeq
{
	public static class Parallel extends Action {
		/** The command holder from which the command will be created. */
		public CommandSetup sequence;
		
		/**
		 * @param sequence A pre-existing command that will be triggered by this action.
		 */
		public Parallel(CommandSetup sequence) {
			super(new ChTrue());
			this.sequence = sequence;
		}
		
		/**
		 * @param requiredSystem The subsystem that this command requires, or null for none.
		 * @param actions The list of actions to be run in the command.
		 */
		public Parallel(SubsystemBase requiredSystem, Action... actions) {
			this(new CommandSetup(requiredSystem, actions));
		}
		
		/**
		 * @param actions The list of actions to be run in the command.
		 */
		public Parallel(Action... actions) {
			this(new CommandSetup(null, actions));
		}
		
		public void onFinish() {
			sequence.c().start();
		}
	}
	
	public static class Sequential extends Action {
		/** The command holder from which the command will be created. */
		public final CommandSetup sequence;
		public CommandBase comToRun;
		public boolean hasStarted = false;
		
		/**
		 * @param sequence A pre-existing command that will be triggered by this action.
		 */
		public Sequential(CommandSetup sequence) {
			super(new ChFalse());
			this.sequence = sequence;
		}
		
		/**
		 * @param requiredSystem The subsystem that this command requires, or null for none.
		 * @param actions The list of actions to be run in the command.
		 */
		public Sequential(SubsystemBase requiredSystem, Action... actions) {
			this(new CommandSetup(requiredSystem, actions));
		}
		
		/**
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
