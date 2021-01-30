package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

/** Shoot *********************************************************************
 * Command for pushing three power cells out during the first part of the Interstellar Accuracy Challenge */
public class Shooter_Spit extends Command {
  /** Configuration Constants ***********************************************/
    private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
    private static final double LPOWER = RobotMap.LOWER_CONVEYOR_POWER;
    private static final double UPOWER = RobotMap.UPPER_CONVEYOR_POWER;
    private static final double SPEED = RobotMap.SHOOTER_SPIT_VELOCITY;

    /** Instance Variables ****************************************************/
    UpperConveyor upperconveyor = Robot.upperconveyor;
    LowerConveyor lowerconveyor = Robot.lowerconveyor;
    Shooter shooter = Robot.shooter;
    Log log = new Log(LOG_LEVEL, SendableRegistry.getName(upperconveyor));

    /** Shooter_Spit *****************************************************************
     * Required subsystems will cancel commands when this command is run. */
    public Shooter_Spit() {
      log.add("Constructor", Log.Level.TRACE);

      requires(lowerconveyor);
      requires(upperconveyor);
      requires(shooter);
    }

    /** initialize **************************************************************
     * Called just before this Command runs the first time */
    protected void initialize() {
      log.add("Initialize", Log.Level.TRACE);
      if (shooter.getSpeed() >= SPEED) {
        lowerconveyor.setPower(LPOWER);
        upperconveyor.setPower(UPOWER);
      }
      else {
        upperconveyor.stop();
        lowerconveyor.stop();
      }
    }

    /** execute ***************************************************************
     * Called repeatedly when this Command is scheduled to run */
    protected void execute() {
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
      lowerconveyor.stop();
      upperconveyor.stop();
      shooter.stop();
    }

    /** interrupted ***********************************************************
     * Called when another command which requires one or more of the same subsystems is scheduled to run */
    protected void interrupted() {
      log.add("Interrupted", Log.Level.TRACE);
      lowerconveyor.stop();
      upperconveyor.stop();
      shooter.stop();
    }
}