/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.superclasses;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.base.commands.CommandBase;

/**
 * @author Sean Zammit
 */
public class SubsystemBase extends Subsystem {
    public int systemID;
    
    public SubsystemBase(int id) {
        super();
        systemID = id;
        CommandBase.addToSubsystemList(this);
    }
    
    public final void initDefaultCommand() {
        onInit();
    }
    
    public void onInit() {
    }
    
    public void setDefCommand(CommandBaseRedCrusade command) {
        setDefaultCommand(command);
    }
}
