package redbacks.robot.subsystems;

import redbacks.arachne.core.references.CommandList;
import redbacks.arachne.lib.checks.CheckNever;
import redbacks.arachne.lib.commands.CommandHolder;
import redbacks.arachne.lib.motors.MotorControllerRB;
import redbacks.arachne.lib.subsystems.SubsystemRB;
import redbacks.robot.drive.ActionDrive;
import redbacks.robot.drive.ActionShift;
import redbacks.robot.drive.ActionSwitchControl;
import redbacks.robot.drive.ActionSwitchControlTo;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import static redbacks.arachne.core.references.RobotMap.*;

/**
 * @author Sean Zammit
 */
public class SubsystemDriver extends SubsystemRB
{
	public boolean 
		isDriverControl = true, 
		isSpeedy = true;

	//Set up motors and solenoids here. Make sure to use the RobotMap.
	public MotorControllerRB l = new MotorControllerRB(driveMotorLID);
	public MotorControllerRB r = new MotorControllerRB(driveMotorRID);

	public RobotDrive driveFront = new RobotDrive(l, r);
	
	public Solenoid shifter = new Solenoid(shifterID);
	
	public CommandHolder
		/** The default drive command. */
		cDrive = newCom(new ActionDrive(new CheckNever())),
		
		cShift = CommandList.newCom(new ActionShift()),
		cShiftFast = CommandList.newCom(new ActionShift(true)),
		cShiftSlow = CommandList.newCom(new ActionShift(false)),
								
		cSwitchControl = CommandList.newCom(new ActionSwitchControl()),
		cSwitchControlToD = CommandList.newCom(new ActionSwitchControlTo(true)),
		cSwitchControlToO = CommandList.newCom(new ActionSwitchControlTo(false));

	public SubsystemDriver() {
		super();
	}

	public void onInit() {
		//Perform anything that needs to be done before the subsystem is used here. 
	}

	//Set up functions using the motors and solenoids here.
}
