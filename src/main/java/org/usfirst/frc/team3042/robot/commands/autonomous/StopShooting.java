package org.usfirst.frc.team3042.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.Turret;

/** Stop Shooting ***************************************************************
 * Command for stopping the turret and shooter at the end of autonomous mode */
public class StopShooting extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;

	/** Instance Variables ****************************************************/
	Turret turret = Robot.turret;
	Shooter shooter = Robot.shooter;
	Limelight limelight = Robot.limelight;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(turret));
	
	/** Turret Stop ***********************************************************
	 * Required subsystems will cancel commands when this command is run. */
	public StopShooting() {
		log.add("Constructor", Log.Level.TRACE);
		requires(turret);
		requires(shooter);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		limelight.led.setNumber(0); //Turn off the Limelight's LEDs
		turret.stop();
		shooter.stop();
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	protected void execute() {}
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute() */
	protected boolean isFinished() {
		return true;
	}
	
	/** end *******************************************************************
	 * Called once after isFinished returns true */
	protected void end() {
		log.add("End", Log.Level.TRACE);
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same subsystems is scheduled to run */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}