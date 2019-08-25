package redbacks.arachne.lib.logic;

public class RedbacksMath
{
	public static double signedPow(double val, double exp) {
		if(val < 0) return -Math.pow(-val, exp);
		return Math.pow(val, exp);
	}
	
	public static double signedSqrt(double val) {
		if(val < 0) return -Math.sqrt(-val);
		return Math.sqrt(val);
	}
}
