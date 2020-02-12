package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.IntakeDeploy;

/** Intake Deploy Toggle *******************************************************
 * Toggles the deploying of the Intake
 */
public class IntakeDeploy_Toggle extends Command {
    /** Configuration Constants ***********************************************/
    private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;

    /** Instance Variables ****************************************************/
    IntakeDeploy intakeDeploy = Robot.intakedeploy;
    Log log = new Log(LOG_LEVEL, SendableRegistry.getName(intakeDeploy));

    /** Intake Deploy Toggle ***************************************************
     * Required subsystems will cancel commands when this command is run.
     */
    public IntakeDeploy_Toggle() {
      log.add("Constructor", Log.Level.TRACE);
      
      requires(intakeDeploy);
    }

    /** initialize ************************************************************
     * Called just before this Command runs the first time
     */
    protected void initialize() {
      log.add("Initialize", Log.Level.TRACE);
      intakeDeploy.extend();
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
      intakeDeploy.retract();
    }

    /** interrupted ***********************************************************
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
      log.add("Interrupted", Log.Level.TRACE);
      intakeDeploy.retract();
    }
}