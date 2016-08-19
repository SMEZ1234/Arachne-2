package redbacks.arachne.lib.logic;

public class Invert implements GettableBoolean
{
	public GettableBoolean parentGettable;
	
	public Invert(GettableBoolean gettable) {
		this.parentGettable = gettable;
	}
	
	public static Invert invert(GettableBoolean gettable) {
		return new Invert(gettable);
	}
	
	public boolean get() {
		return !parentGettable.get();
	}
}
