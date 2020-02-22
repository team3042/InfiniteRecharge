package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Turret;
import org.usfirst.frc.team3042.robot.subsystems.TurretEncoder;

/** Turret Zero *******************************************************
 * Command for moving the turret back to it's zero position.
 */
public class Turret_Zero extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
	private static final double POWER = RobotMap.TURRET_MANUAL_POWER / 2;
	private static final double TOLERANCE = RobotMap.ZERO_TOLERANCE;

	/** Instance Variables ****************************************************/
	Turret turret = Robot.turret;
	TurretEncoder encoder = turret.getEncoder();  
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	
	/** Turret Zero ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Turret_Zero() {
		log.add("Constructor", Log.Level.TRACE);
		requires(turret);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		if (encoder.getPosition() > 0) {
			turret.setPower(-1 * POWER);
		}
		else if (encoder.getPosition() < 0) {
			turret.setPower(POWER);
		}
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return Math.abs(encoder.getPosition()) <= TOLERANCE;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		turret.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		turret.stop();
	}
}