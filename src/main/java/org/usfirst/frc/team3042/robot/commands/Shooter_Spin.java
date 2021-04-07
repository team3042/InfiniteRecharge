package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;

/** Shooter Spin **************************************************************
 * Spins up the shooter to the desired velocity */
public class Shooter_Spin extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER;
	private static final double kP = RobotMap.kP_SHOOTER_SPEED;
	private static final double SPEED = RobotMap.SHOOTER_VELOCITY;
	
	/** Instance Variables ****************************************************/
	Shooter shooter = Robot.shooter;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(shooter));

	double error;
	double correction;
	
	/** Shooter Spin **********************************************************
	 * Required subsystems will cancel commands when this command is run. */
	public Shooter_Spin() {
		log.add("Constructor", Log.Level.TRACE);
		requires(shooter);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	protected void execute() {
		error = (SPEED - shooter.getSpeed());

		correction = kP * error;

		correction = Math.min(1, correction);
		correction = Math.max(0, correction);

		shooter.setPower(correction);
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
		shooter.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		shooter.stop();
	}
}