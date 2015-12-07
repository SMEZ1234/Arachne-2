package redbacks.arachne.core;

import java.util.ArrayList;

import redbacks.arachne.lib.subsystems.SubsystemRB;
import redbacks.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * CommandBase creates and stores each subsystem and the instance of the operator interface, or OI.
 * 
 * @author Sean Zammit
 */
public abstract class CommandBase extends Command
{
	/** The instance of the operator interface. This is used to map inputs to functions. */
	public static OI oi;

	/**
	 * A full list of subsystems on the robot. 
	 * This is only used at this point to interrupt all subsystems, and add information from all subsystems to the dashboard.
	 */
	public static ArrayList<SubsystemRB> subsystemList = new ArrayList<SubsystemRB>();

	/** Do not remove. This is used to set up each subsystem. */
	public static int id = 0;

	//Create an instance of each subsystem here.
	public static SubsystemSensors sensors = new SubsystemSensors();
	public static SubsystemDriver driver = new SubsystemDriver();
	
	/**
	 * Called when the robot is first initialised.
	 * In this case, it sets up the subsystems and the instance of the OI.
	 */
	public static void init() {
		sensors.setDefCommand(sensors.cReadSensors.c());
		driver.setDefCommand(driver.cDrive.c());
		
		//Don't move or change this. EVER. Without it, no operator interface, no mapping, therefore "robot no move."
		oi = new OI();

		//Calls the method in SubsystemSensors that starts all sensors. Do not remove.
		sensors.initSensors();

		//Adds information from all subsystems to the dashboard.
		for(int a = 0; a < subsystemList.size(); a++) SmartDashboard.putData(subsystemList.get(a));
		SmartDashboard.putNumber("Number of subsystems", id);
	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}
}
