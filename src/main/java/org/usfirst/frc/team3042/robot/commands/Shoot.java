package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

/** Shoot *********************************************************************
 * Command for pushing power cells into the shooter
 */
public class Shoot extends Command {
    /** Configuration Constants ***********************************************/
    private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
    private static final double LPOWER = RobotMap.LOWER_CONVEYOR_POWER;
    private static final double UPOWER = RobotMap.UPPER_CONVEYOR_POWER;
    private static final double SPEED = RobotMap.SHOOTER_VELOCITY;
    private static final double TOLERANCE = RobotMap.TURRET_ANGLE_TOLERANCE;
    private static final double VELOCITY_TOLERANCE = RobotMap.SHOOTER_VELOCITY_TOLERANCE;

    /** Instance Variables ****************************************************/
    UpperConveyor upperconveyor = Robot.upperconveyor;
    LowerConveyor lowerconveyor = Robot.lowerconveyor;
    Shooter shooter = Robot.shooter;
    Limelight limelight = Robot.limelight;
    Log log = new Log(LOG_LEVEL, SendableRegistry.getName(upperconveyor));

    /** Shoot *****************************************************************
     * Required subsystems will cancel commands when this command is run.
     */
    public Shoot() {
      log.add("Constructor", Log.Level.TRACE);

      requires(upperconveyor);
      requires(lowerconveyor);
    }

	/** initialize **************************************************************
     * Called just before this Command runs the first time
     */
    protected void initialize() {
      log.add("Initialize", Log.Level.TRACE);
    }

    /** execute ***************************************************************
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
      if (limelight.returnValidTarget() == 1.0 && Math.abs(limelight.returnHorizontalError()) <= TOLERANCE && Math.abs(shooter.motor.getEncoder().getVelocity() - SPEED) <= VELOCITY_TOLERANCE) {
        lowerconveyor.setPower(LPOWER);
        upperconveyor.setPower(UPOWER);
      }
      else {
        upperconveyor.stop();
        lowerconveyor.stop();
      }
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
      upperconveyor.stop();
      lowerconveyor.stop();
    }

    /** interrupted ***********************************************************
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
      log.add("Interrupted", Log.Level.TRACE);
      upperconveyor.stop();
      lowerconveyor.stop();
    }
}