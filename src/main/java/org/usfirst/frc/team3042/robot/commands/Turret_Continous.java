package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.Turret;

/** Turret Continous **********************************************************
 * Command for correcting the reported angle of error with the turret */
public class Turret_Continous extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
	private static final double kP = RobotMap.kP_TURRET;
	private static final double kI = RobotMap.kI_TURRET;
	private static final double kD = RobotMap.kD_TURRET;
	private static final double tolerance = RobotMap.TURRET_ANGLE_TOLERANCE;
	private static final double searchPower = RobotMap.TURRET_SEARCH_POWER;
	private static final double maxPower = RobotMap.TURRET_MAX_POWER;
	private static final double maxAngle = RobotMap.TURRET_MAX_ANGLE;

	/** Instance Variables ****************************************************/
	Turret turret = Robot.turret;

	Limelight limelight = Robot.limelight; 

	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	  
	Shooter shooter = Robot.shooter;
	double error;
	double correction;
	double derivative; // Derivative is the instantaneous rate of change in error
	double integral = 0; // Integral is the sum of all errors
	double previousError;

	boolean autonomous;

	double previousReading;
	int counter = 0;
	
	/** Turret Continous ******************************************************
	 * Required subsystems will cancel commands when this command is run. */
	public Turret_Continous(boolean auto) {
		log.add("Constructor", Log.Level.TRACE);
		requires(turret);
		turret.reset();
		autonomous = auto;
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		limelight.led.setNumber(3); // Turn on the Limelight's LEDs
		counter = 0;
		if (limelight.returnValidTarget() == 0) {
			turret.setPower(-1 * searchPower);
		}
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	protected void execute() {
		error = limelight.returnHorizontalError(); // Read the angle of error from the Limelight
		if (limelight.returnValidTarget() == 1 && Math.abs(error) > tolerance) { //PID Loop for tracking the target
			integral += error * 0.2; // Add the current error to the integral
			derivative = (error - previousError) / .02;

			correction = (kP * error) + (kI * integral) + (kD * derivative);
			correction = Math.min(maxPower, correction);
			correction = Math.max(-1 * maxPower, correction);

			turret.setPower(correction);
		}
		else if (counter < 5) {
			if(turret.countsToDegrees(turret.getPosition()) > maxAngle) { // Max positive angle of the turret has been reached
				turret.setPower(-1 * searchPower);
			}
			else if(turret.countsToDegrees(turret.getPosition()) < -1 * maxAngle) { //Max negative angle of the turret has been reached
				turret.setPower(searchPower);
			}
			previousError = error; // set the previous error equal to the current error before starting the loop over and getting a new current error
			if (previousReading == turret.getSpeed()) {
				counter += 1;
			}
			else {
				counter = 0;
			}
			previousReading = turret.getSpeed();
		}
		else {
			if(!autonomous) {
				counter = 0;
			}
			else {
				turret.stop();
			}
		}
	}

	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute() */
	protected boolean isFinished() {
		return false;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		turret.stop();
		limelight.led.setNumber(0);
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		turret.stop();
		limelight.led.setNumber(0);
	}
}