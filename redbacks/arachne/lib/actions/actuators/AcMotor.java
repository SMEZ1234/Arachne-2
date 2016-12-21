package redbacks.arachne.lib.actions.actuators;

import redbacks.arachne.lib.actions.Action;
import redbacks.arachne.lib.checks.Check;
import redbacks.arachne.lib.checks.ChFalse;
import redbacks.arachne.lib.checks.ChTrue;
import redbacks.arachne.lib.motors.CtrlMotor;

/**
 * Holds all motor actions.
 * 
 * @author Sean Zammit
 */
public class AcMotor
{
	/**
	 * An action used to immediately set a motor to a certain speed.
	 * For gradual speed changes, use {@link RampTime AcMotor.RampTime} or {@link RampAccel AcMotor.RampAccel}.
	 * 
	 * @author Sean Zammit
	 */
	public static class Set extends Action
	{
		private CtrlMotor motor;
		private double speed;

		/**
		 * Constructor for an action that repeatedly sets a motor to a specified speed.
		 * 
		 * @param motor The motor being set.
		 * @param speed The speed that the motor should be set to.
		 * @param check The condition that will finish the action.
		 */
		public Set(CtrlMotor motor, double speed, Check check) {
			super(check);
			this.motor = motor;
			this.speed = speed;
		}

		public void onStart() {
			motor.set(speed, command);
		}

		public void onRun() {
			motor.set(speed, command);
		}
	}

	/**
	 * An action that stops a motor.
	 * 
	 * @author Sean Zammit
	 */
	public static class Disable extends Set
	{
		/**
		 * Constructor for an action that stops a motor.
		 * 
		 * @param motor The motor being disabled.
		 */
		public Disable(CtrlMotor motor) {
			super(motor, 0, new ChTrue());
		}
	}

	/**
	 * An action used to ramp a motor to a certain speed over a set period of time.
	 * 
	 * @author Sean Zammit
	 */
	public static class RampTime extends Action
	{
		private CtrlMotor motor;
		private double speed, time, startSpeed, startTime;
		private boolean shouldEnd;

		/**
		 * Constructor for an action that will ramp a motor to a certain speed over a set period of time before finishing.
		 * 
		 * @param motor The motor being set.
		 * @param speed The speed that the motor should reach at the end of the period.
		 * @param time The time over which the change in speed should occur.
		 */
		public RampTime(CtrlMotor motor, double speed, double time) {
			this(motor, speed, time, new ChFalse(), true);
		}

		/**
		 * Constructor for an action that will ramp a motor to a certain speed over a set period of time, then finish when specified conditions are met.
		 * 
		 * @param motor The motor being set.
		 * @param speed The speed that the motor should reach at the end of the period.
		 * @param time The time over which the change in speed should occur.
		 * @param check A condition that will finish the action regardless of time elapsed.
		 * @param shouldEndWhenComplete Whether the action should automatically finish once the time is up.
		 */
		public RampTime(CtrlMotor motor, double speed, double time, Check check, boolean shouldEndWhenComplete) {
			super(check);
			this.motor = motor;
			this.speed = speed;
			this.time = time;
			this.shouldEnd = shouldEndWhenComplete;
		}

		public void onStart() {
			startSpeed = motor.get();
			startTime = command.timeSinceInitialized();
		}

		public void onRun() {
			double actionTime = command.timeSinceInitialized() - startTime;
			if(speed >= startSpeed) motor.set(Math.min(startSpeed + actionTime / time * Math.abs(speed - startSpeed), speed), command);
			else motor.set(Math.max(startSpeed - actionTime / time * Math.abs(speed - startSpeed), speed), command);
		}

		public boolean isDone() {
			return shouldEnd && command.timeSinceInitialized() - startTime > time;
		}

		public void onFinish() {
			motor.set(speed, command);
		}
	}

	/**
	 * An action used to ramp a motor to a certain speed at a set acceleration.
	 * 
	 * @author Sean Zammit
	 */
	public static class RampAccel extends Action
	{
		private CtrlMotor motor;
		private double speed, accel, startSpeed, startTime;
		private boolean shouldEnd;

		/**
		 * Constructor for an action that will ramp a motor to a certain speed at a set acceleration before finishing.
		 * 
		 * @param motor The motor being set.
		 * @param speed The speed that the motor should reach at the end of the acceleration.
		 * @param accelPerSec The acceleration of the motor per second. e.g. If the motor starts at -1, and this is set to 0.5, it will take 4 seconds to reach 1.
		 */
		public RampAccel(CtrlMotor motor, double speed, double accelPerSec) {
			this(motor, speed, accelPerSec, new ChFalse(), true);
		}

		/**
		 * Constructor for an action that will ramp a motor to a certain speed at a set acceleration, then finish when specified conditions are met.
		 * 
		 * @param motor The motor being set.
		 * @param speed The speed that the motor should reach at the end of the acceleration.
		 * @param accelPerSec The acceleration of the motor per second. e.g. If the motor starts at -1, and this is set to 0.5, it will take 4 seconds to reach 1..
		 * @param check A condition that will finish the action regardless of speed reached.
		 * @param shouldEndWhenComplete Whether the action should automatically finish once the speed is reached.
		 */
		public RampAccel(CtrlMotor motor, double speed, double accelPerSec, Check check, boolean shouldEndWhenComplete) {
			super(check);
			this.motor = motor;
			this.speed = speed;
			this.accel = accelPerSec;
			this.shouldEnd = shouldEndWhenComplete;
		}

		public void onStart() {
			startSpeed = motor.get();
			startTime = command.timeSinceInitialized();
		}

		public void onRun() {
			double actionTime = command.timeSinceInitialized() - startTime;
			if(speed >= startSpeed) motor.set(Math.min(startSpeed + actionTime * accel, speed), command);
			else motor.set(Math.max(startSpeed - actionTime * accel, speed), command);
		}

		public boolean isDone() {
			return shouldEnd && motor.get() == speed;
		}

		public void onFinish() {
			motor.set(speed, command);
		}
	}
}
