package redbacks.arachne.lib.subsystems;

import java.util.ArrayList;

import redbacks.arachne.core.CommandBase;
import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.commands.CommandSetup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The main subsystem class. Provides a base for all subsystems to be built on.
 * Do not create instances of this class.
 * 
 * @author Sean Zammit
 */
public class SubsystemBase extends Subsystem
{
	private ArrayList<SubsystemBase> conflictSystems = new ArrayList<SubsystemBase>();
	
	public SubsystemBase(SubsystemBase... childSystems) {
		super();
		CommandBase.subsystemList.add(this);
		setDefaultCommand(new CommandSetup(this, new AcDoNothing()).c());
		for(SubsystemBase subsystem : childSystems) {
			this.conflictSystems.add(subsystem);
			subsystem.conflictSystems.add(this);
		}
	}

	public final void initDefaultCommand() {
		onStart();
	}

	/**
	 * A blank method that can be overwritten by individual subsystems.
	 * Called once on initialisation.
	 */
	public void onStart() {
	}

	/**
	 * Sets the default command. This method exists to force the command to be an instance of CommandBase.
	 * 
	 * @param command The command that should be run on this subsystem when no other commands are running.
	 */
	public void setDefaultCommand(CommandBase command) {
		super.setDefaultCommand(command);
	}
	
	public void interruptRelatedSubsystems() {
		for(SubsystemBase subsystem : conflictSystems) subsystem.getCurrentCommand().cancel();
	}
	
	public boolean isSubsystemBusy() {
		return getCurrentCommand() != getDefaultCommand();
	}
	
	public Command getDefaultCommand() {
		return super.getDefaultCommand();
	}
}
