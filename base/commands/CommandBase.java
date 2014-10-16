package edu.wpi.first.wpilibj.base.commands;

import edu.wpi.first.wpilibj.base.OI;
import edu.wpi.first.wpilibj.base.commands.autonomous.Autonomous;
import edu.wpi.first.wpilibj.base.commands.drive.CommandDrive;
import edu.wpi.first.wpilibj.base.subsystems.SubsystemDriver;
import edu.wpi.first.wpilibj.base.subsystems.SubsystemRC;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.superclasses.CommandRC;
import edu.wpi.first.wpilibj.superclasses.SubsystemBase;

/**
 * DO NOT EXTEND THIS CLASS.
 * Use CommandRC instead.
 *
 * CommandBase creates and stores each subsystem. 
 * To access a subsystem elsewhere in your code in your code use either:
 *     CommandBase.SUBSYSTEM or
 *     CommandBase.subsystemList[SUBSYSTEM.systemID]
 * 
 * @author Sean Zammit
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    
    public static SubsystemBase[] subsystemList = new SubsystemBase[]{};
    
    //Create an instance of each subsystem here.
    //Just as a note, this method of subsystem setup is quite experimental, so be wary of bugs.
    public static SubsystemRC sensors = (SubsystemRC) setupNewSubsystem(new SubsystemRC(0), new CommandReadSensors(0));
    public static SubsystemDriver driver = (SubsystemDriver) setupNewSubsystem(new SubsystemDriver(1), new CommandDrive(1));
    
    public static Autonomous autonomous;
    
    public static void init() {
        //Don't move or change this. EVER.
        oi = new OI();
        
        //Set the sequence of commands run by autonomous here.
        autonomous = new Autonomous(null);
        
        //Calls the method in SubsystemRC that starts all sensors. Do not remove.
        SubsystemRC.initSensors();
        
        //Not completely sure what this does. Leave it alone as it isn't necessary.
        SmartDashboard.putData(subsystemList[0]);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
    
    /*
     * Used when subsystem instances are created to create a complete list of subsystems.
     */
    public static void addToSubsystemList(SubsystemBase subsystem) {
        SubsystemBase[] newList = new SubsystemBase[Math.max(subsystemList.length, subsystem.systemID + 1)];
        for(int a = 0; a < subsystemList.length; a++) {
            newList[subsystemList[a].systemID] = subsystemList[a];
        }
        newList[subsystem.systemID] = subsystem;
        subsystemList = newList;
    }

    /*
     * Handles necessary procedures when creating a new subsystem.
     */
    private static SubsystemBase setupNewSubsystem(SubsystemBase subsystem, CommandRC command) {
        subsystem.setDefCommand(command);
        return subsystemList[subsystem.systemID];
    }
}
