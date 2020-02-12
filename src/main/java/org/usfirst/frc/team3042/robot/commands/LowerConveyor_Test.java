package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;

/** Testing Lower Conveyor *******************************************************
 * Move Lower Conveyor
 */
public class LowerConveyor_Test extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_COLOR_SENSOR;
	private static final double speed = RobotMap.LOWER_CONVEYOR_POWER;
	
	/** Instance Variables ****************************************************/
	LowerConveyor conveyor = Robot.lowerconveyor;
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(conveyor));
	
	/** Testing Lower Conveyor ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public LowerConveyor_Test() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(conveyor);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		conveyor.setPower(speed);
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
		conveyor.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		conveyor.stop();
	}
}