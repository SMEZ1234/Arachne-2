/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.superclasses.CommandBaseRedCrusade;

/**
 *
 * @author Author
 */
public class CommandTemplate extends CommandBaseRedCrusade {

    public CommandTemplate(int requiredSystem) {
        super(requiredSystem);
    }
    
    //Set up what the robot will do while this command is running.
    protected void execute() {
    }

    //Determine the conditions that will stop this command.
    protected boolean isFinished() {
        return false;
    }    
}
