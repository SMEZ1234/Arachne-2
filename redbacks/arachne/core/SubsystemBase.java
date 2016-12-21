package redbacks.arachne.core;

import java.util.ArrayList;

import redbacks.arachne.lib.actions.AcDoNothing;
import redbacks.arachne.lib.commands.CommandSetup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The main subsystem class. Provides a base for all subsystems to be built on.
 * 
 * @author Sean Zammit
 */
public class SubsystemBase extends Subsystem
{
	/**
	 * A list of 'conflicting' subsystems. Its purpose is to allow you to have child subsystems of a major component, but still get commands interrupting any conflicting systems.
	 * Note that this will not work for any more than 2 levels of subsystem. It allows a subsystem's immediate children to interrupt it, but not their children. It's public in case you need to implement this yourself though.
	 */
	public ArrayList<SubsystemBase> conflictSystems = new ArrayList<SubsystemBase>();

	/**
	 * Replaces {@link Subsystem Subsystem}'s constructor in order to populate {@link ArachneRobot ArachneRobot}'s {@link ArachneRobot#subsystemList subsystemList}, set an empty default command, and set up the list of conflicting subsystems.
	 * 
	 * @param childSystems A list of child subsystems. See {@link #conflictSystems conflictSystems} for more details.
	 */
	public SubsystemBase(SubsystemBase... childSystems) {
		super();
		ArachneRobot.subsystemList.add(this);
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
	public void onStart() {}

	/**
	 * Used by Arachne to interrupt any immediate child and parent subsystems when a command begins running on this subsystem.
	 * See {@link #conflictSystems conflictSystems} for more details.
	 */
	public void interruptRelatedSubsystems() {
		for(SubsystemBase subsystem : conflictSystems) subsystem.getCurrentCommand().cancel();
	}

	/**
	 * Used to check whether the subsystem has a command running on it. Exists in case it ever is useful.
	 * 
	 * @return Whether there is a command running on this subsystem that isn't the subsystem's default command.
	 */
	public boolean isSubsystemBusy() {
		return getCurrentCommand() != getDefaultCommand();
	}

	public void setDefaultCommand(Command command) {
		super.setDefaultCommand(command);
	}

	public Command getDefaultCommand() {
		return super.getDefaultCommand();
	}
}
