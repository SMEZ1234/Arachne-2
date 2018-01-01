package redbacks.arachne.core.api;

import java.util.ArrayList;

public class RobotAPI
{
	private static ArrayList<RobotExtender> preList = new ArrayList<RobotExtender>();
	private static ArrayList<RobotExtender> postList = new ArrayList<RobotExtender>();
	
	public static enum Order {
		PRE, POST
	}
	
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
