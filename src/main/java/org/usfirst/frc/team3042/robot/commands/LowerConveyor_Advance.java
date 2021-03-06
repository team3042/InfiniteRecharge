package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;
import org.usfirst.frc.team3042.robot.subsystems.UltrasonicSensor;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

/** Advance Lower Conveyor ****************************************************
 * Advances the Lower Conveyor once it detects a power cell */
public class LowerConveyor_Advance extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LOWER_CONVEYOR;
	private static final double speed = RobotMap.LOWER_CONVEYOR_POWER;
	private static final double duration = RobotMap.CONVEYOR_ADVANCE_DURATION;
	
	/** Instance Variables ****************************************************/
	UltrasonicSensor sensor = Robot.ultrasonicsensor;
	LowerConveyor conveyor = Robot.lowerconveyor;
	UpperConveyor upperConveyor = Robot.upperconveyor;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(conveyor));
	Timer timer = new Timer();
	boolean moving = false;
	
	/** Advance Lower Conveyor ************************************************
	 * Required subsystems will cancel commands when this command is run. */
	public LowerConveyor_Advance() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(conveyor);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		timer.reset();
	}

	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run */
	protected void execute() {
		if(sensor.isPowerCellIn()){
			if (!moving) {
				conveyor.setPower(speed);
				timer.start();
			}
			moving = true;
		}
		if(timer.get() >= duration){
			conveyor.stop();
			timer.stop();
			timer.reset();
		}
		if (!sensor.isPowerCellIn()) {
			moving = false;
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
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}