package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.ControlPanelWheel;

/** Position Control Manual ***************************************************
 * Command for rotating the control panel to the assigned color manually (Without the use of a color sensor)
 */
public class PositionControl_Manual extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CONTROL_PANEL_WHEEL;
	private static final double POWER = RobotMap.POSITION_CONTROL_POWER;
	
	/** Instance Variables ****************************************************/
  	ControlPanelWheel cpwheel = Robot.cpwheel;
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(cpwheel));
	
	/** Position Control Manual ***********************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public PositionControl_Manual() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(cpwheel);
	}

	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
    	cpwheel.setPower(POWER);
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
		cpwheel.stop();
	}

	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		cpwheel.stop();
	}
}