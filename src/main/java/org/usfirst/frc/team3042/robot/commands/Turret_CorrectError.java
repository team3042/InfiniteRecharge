package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Turret;
import org.usfirst.frc.team3042.robot.subsystems.TurretEncoder;

/** Turret Correct Error *******************************************************
 * Command for correcting the reported angle of error with the turret
 */

public class Turret_CorrectError extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
	private static final double SPEED = RobotMap.TURRET_SPEED;

	/** Instance Variables ****************************************************/
	Turret turret = Robot.turret;
	Limelight limelight = Robot.limelight;
	TurretEncoder encoder = turret.getEncoder();  
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	  
	double error;
	
	/** Turret Correct Error ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Turret_CorrectError() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(turret);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		encoder.reset();
		error = encoder.degreesToCounts(limelight.returnHorizontalError());
		SmartDashboard.putNumber("error", error);
		if(error > 0) {
			turret.setPower(SPEED);
		}
		else if (error < 0) {
			turret.setPower(-1 * SPEED);
		}
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		SmartDashboard.putNumber("position", encoder.getPosition());
	}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		if(error > 0) {
			return encoder.getPosition() <= error * -1;
		}
		else {
			return encoder.getPosition() >= error * -1;
		}
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