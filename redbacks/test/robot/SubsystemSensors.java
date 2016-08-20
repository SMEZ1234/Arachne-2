package redbacks.test.robot;

import redbacks.arachne.core.SubsystemBase;
import redbacks.arachne.lib.sensors.SenCANEncoder;

import static redbacks.test.robot.Robot.*;

/**
 * The sensor subsystem. Only put sensors here.
 * 
 * @author Sean Zammit
 */
public class SubsystemSensors extends SubsystemBase
{
	//Create sensors here. Make sure to use the RobotMap.
	//Drive
	public SenCANEncoder driveLEncoder = new SenCANEncoder(driver.left);
	public SenCANEncoder driveREncoder = new SenCANEncoder(driver.right);
		
	//Turret
//	public SenCANDigitalInput turretMagLSwitch = new SenCANDigitalInput(launcher.shooterL, true);
//	public SenCANDigitalInput turretMagRSwitch = new SenCANDigitalInput(launcher.shooterL, false);
//	public SenCANDigitalInput turretBaseSwitch = new SenCANDigitalInput(launcher.shooterR, true);
//	public SenCANEncoder turretPanEncoder = new SenCANEncoder(turret.pan);
//	public SenCANEncoder turretTiltEncoder = new SenCANEncoder(turret.tilt);
	
	public SubsystemSensors() {
		super();
	}

	//Set up functions using the sensors here. For example, if sensors should be grouped because they fulfill the same function.
}