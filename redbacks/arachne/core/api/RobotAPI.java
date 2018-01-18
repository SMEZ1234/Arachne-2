package redbacks.arachne.core.api;

import java.util.ArrayList;

import redbacks.arachne.core.ArachneRobot;

/**
 * Contains functions used to integrate secondary Arachne modules without overriding code in the core module.
 * Though teams are welcome to use its functionality, you can get the same results by just extending and overriding the relevant classes.
 *
 * @author Sean Zammit
 */
public class RobotAPI
{
	/** A list of {@link RobotExtender RobotExtenders} to have their initialisation and execution methods called before user-created robot code. */
	private static ArrayList<RobotExtender> preList = new ArrayList<RobotExtender>();
	
	/** A list of {@link RobotExtender RobotExtenders} to have their initialisation and execution methods called after user-created robot code. */
	private static ArrayList<RobotExtender> postList = new ArrayList<RobotExtender>();
	
	/**
	 * An enum used to determine when a {@link RobotExtender} is called.
	 * PRE will be called before user-created code in a robot file extending {@link ArachneRobot}.
	 * POST will be called after user-created code in a robot file extending {@link ArachneRobot}.
	 *
	 * @author Sean Zammit
	 */
	public static enum Order {
		PRE, POST
	}
	
	/**
	 * Adds a {@link RobotExtender} to the relevant list to be called during initialisation and execution.
	 *
	 * @param extension The instance of a class overriding RobotExtender to be called.
	 * @param order When the extension should be called. See {@link Order}.
	 */
	public static void addExtension(RobotExtender extension, Order order) {
		switch(order) {
			case POST:
				postList.add(extension);
				break;
			case PRE:
				preList.add(extension);
				break;
		}
	}
	
	/**
	 * Gets a list of {@link RobotExtender RobotExtenders} to have their initialisation and execution methods called by {@link ArachneRobot}.
	 *
	 * @param order The current stage of execution. See {@link Order}.
	 * @return The relevant list of RobotExtenders.
	 */
	public static ArrayList<RobotExtender> getExtensions(Order order) {
		switch(order) {
			case POST:
				return postList;
			case PRE:
				return preList;
			default:
				return new ArrayList<RobotExtender>();
		}
	}
}
