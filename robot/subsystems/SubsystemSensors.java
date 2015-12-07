package redbacks.robot.subsystems;

import redbacks.arachne.lib.commands.CommandHolder;
import redbacks.arachne.lib.subsystems.SubsystemRB;
import redbacks.robot.sensors.ActionReadSensors;
import static redbacks.arachne.core.references.RobotMap.*;

/**
 * The sensor subsystem. Only put sensors here.
 * 
 * @author Sean Zammit
 */
public class SubsystemSensors extends SubsystemRB
{
	//Create sensors here. Make sure to use the RobotMap.
	/*public Gyro gyro = new Gyro(gyroID);
	
	public Encoder driveEncoderL = new Encoder(driveEncoderL3ID, driveEncoderL1ID);
	public Encoder driveEncoderR = new Encoder(driveEncoderR3ID, driveEncoderR1ID);*/
	
	/** The only command that should be run on this subsystem. Sends sensor data to the SmartDashboard. */
	public CommandHolder cReadSensors = newCom(new ActionReadSensors());
	
	public SubsystemSensors() {
		super();
	}

	/**
	 * Called by CommandBase on initialisation. Reset/set up all sensors here.
	 */
	public final void initSensors() {
		/*gyro.reset();
		driveEncoderL.reset();
		driveEncoderR.reset();*/
	}

	//Set up functions using the sensors here. For example, if sensors should be grouped because they fulfil the same function.
}