package org.usfirst.frc.team3042.robot.commands;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.ClimbingWinch;

/** Climbing Winch Wind ************************************************************
 * Winds the climbing winch
 */
public class ClimbingWinch_Wind extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CLIMBING_WINCH;
	//private static final double TIME = RobotMap.WINCH_WIND_TIME;
	private static final double POWER = RobotMap.CLIMBING_WINCH_POWER;
  
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	ClimbingWinch winch = Robot.climbingwinch;
	//Timer timer = new Timer();
	int direction;
	
	/** Climbing Winch Wind ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public ClimbingWinch_Wind(int direction) {
		log.add("Constructor", Log.Level.TRACE);
    	requires(winch);
    	this.direction = direction;
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		winch.setPower(direction * POWER);
		
		//timer.reset();
		//timer.start();
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
		//return timer.get() > TIME;
		return false;
	}

	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
    	log.add("End", Log.Level.TRACE);
    	winch.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
    	log.add("Interrupted", Log.Level.TRACE);
    	winch.stop();
	}
}