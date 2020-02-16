package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.ClimbingHook;

/** Climbing Hook Manual ************************************************************
 * Command for raising and lowering the climber
 */
public class ClimbingHook_Manual extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CLIMBING_HOOK;
	private static final double POWER = RobotMap.CLIMBING_HOOK_POWER;
  
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	ClimbingHook hook = Robot.climbinghook;
  	int direction;
	
	/** Climbing Hook Manual ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public ClimbingHook_Manual(int direction) {
		log.add("Constructor", Log.Level.TRACE);
    	requires(hook);
    	this.direction = direction;
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		hook.setPower(direction * POWER);
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
		return false;
	}

	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
    	log.add("End", Log.Level.TRACE);
    	hook.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
    	log.add("Interrupted", Log.Level.TRACE);
    	hook.stop();
	}
}