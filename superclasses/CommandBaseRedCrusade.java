package edu.wpi.first.wpilibj.superclasses;

import edu.wpi.first.wpilibj.base.commands.CommandBase;

/**
 * Creates a standard setup for all commands.
 * All atomic should subclass CommandBaseRedCrusade.
 * 
 * @author Sean Zammit
 */
public abstract class CommandBaseRedCrusade extends CommandBase {

    //Command to run when this command is started
    public CommandBaseRedCrusade command;
    
    //Command to run when this command is finished
    public CommandBaseRedCrusade commandStart;
    
    public boolean isStartCommandComplete = true;
    
    public CommandBaseRedCrusade(int requiredSystem) {
        super();
        if(requiredSystem != -1) {
            requires(subsystemList[requiredSystem]);
        }
    }
    
    public CommandBaseRedCrusade(int requiredSystem, CommandBaseRedCrusade comOnExit, CommandBaseRedCrusade comOnStart) {
        this(requiredSystem);
        command = comOnExit;
        commandStart = comOnStart;
        isStartCommandComplete = false;
    }

    public CommandBaseRedCrusade(int requiredSystem, CommandBaseRedCrusade comOnExit) {
        this(requiredSystem);
        command = comOnExit;
    }

    protected void initialize() {
        if(commandStart != null) {
            commandStart.start();
        }
    }

    protected abstract void execute();

    protected abstract boolean isFinished();

    protected void end() {
        if(command != null) {
            command.start();
        }
    }

    protected void interrupted() {        
    }
}
