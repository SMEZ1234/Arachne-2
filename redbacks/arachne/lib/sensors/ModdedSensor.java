package redbacks.arachne.lib.sensors;

import java.util.function.DoubleUnaryOperator;

public class ModdedSensor extends NumericSensor
{
	private final NumericSensor sensor;
	private DoubleUnaryOperator operator;
	
	public ModdedSensor(NumericSensor sensor, DoubleUnaryOperator operator) {
		this.sensor = sensor;
		this.operator = operator;
	}
	
	protected double getSenVal() {
		return operator.applyAsDouble(sensor.get());
	}
	
	public void set(double value) {
		sensor.set(value);
	}
}
