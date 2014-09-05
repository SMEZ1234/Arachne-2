package edu.wpi.first.wpilibj.superclasses;

import edu.wpi.first.wpilibj.base.commands.CommandBase;

/**
 * Creates a standard setup for all commands.
 * All commands should subclass CommandRC.
 * 
 * @author Sean Zammit
 */
public abstract class CommandRC extends CommandBase {

    //Command to run when this command is started
    public CommandRC command;
    
    //Command to run when this command is finished
    public CommandRC commandStart;
    
    public boolean isStartCommandComplete = true;
    
    public CommandRC(int requiredSystem) {
        super();
        if(requiredSystem != -1) {
            requires(subsystemList[requiredSystem]);
        }
    }
    
    public CommandRC(int requiredSystem, CommandRC comOnExit, CommandRC comOnStart) {
        this(requiredSystem);
        command = comOnExit;
        commandStart = comOnStart;
        isStartCommandComplete = false;
    }

    public CommandRC(int requiredSystem, CommandRC comOnExit) {
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
