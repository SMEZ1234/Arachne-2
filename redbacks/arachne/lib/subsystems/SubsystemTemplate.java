package redbacks.arachne.lib.subsystems;

/**
 * @author Author
 */
public class SubsystemTemplate extends SubsystemBase
{
	//Set up motors and solenoids here. Make sure to use the RobotMap.

	public SubsystemTemplate() {
		super();
	}

	public void onStart() {
		//Perform anything that needs to be done before the subsystem is used here. 
		//Do not start sensors. Do that in SubsystemSensors.initSensors().
	}
}
