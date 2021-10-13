package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
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
 * Command for pushing power cells into the shooter */
public class Shoot extends Command {
  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
  private static final double LPOWER = RobotMap.LOWER_CONVEYOR_POWER;
  private static final double UPOWER = RobotMap.UPPER_CONVEYOR_POWER;
  private static final double SPEED = RobotMap.SHOOTER_VELOCITY;
  private static final double TOLERANCE = RobotMap.TURRET_ANGLE_TOLERANCE;

  /** Instance Variables ****************************************************/
  UpperConveyor upperconveyor = Robot.upperconveyor;
  LowerConveyor lowerconveyor = Robot.lowerconveyor;
  Shooter shooter = Robot.shooter;
  Limelight limelight = Robot.limelight;
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(upperconveyor));
  Timer timer = new Timer();

  boolean onTarget = false;
  boolean timedShoot;
  double time;

  /** Shoot *****************************************************************
   * Required subsystems will cancel commands when this command is run. */
  public Shoot() {
    log.add("Constructor", Log.Level.TRACE);

    onTarget = false;
    timedShoot = false;
  }
  //Overloaded constructor that allows us to shoot for a set amount of time; useful for autonomous shooting!
  public Shoot(double t) {
    log.add("Constructor", Log.Level.TRACE);

    time = t;
    timedShoot = true;
    onTarget = false;
  }
  //Overloaded constructor that allows us to shoot without using the turret for targeting; useful for close-range shots!
  public Shoot(boolean noTargeting) {
    log.add("Constructor", Log.Level.TRACE);

    onTarget = noTargeting;
    timedShoot = false;
  }

  /** initialize **************************************************************
   * Called just before this Command runs the first time */
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
  }

  /** execute ***************************************************************
   * Called repeatedly when this Command is scheduled to run */
  protected void execute() {
    if (!onTarget && limelight.returnValidTarget() == 1.0 && Math.abs(limelight.returnHorizontalError()) <= TOLERANCE && shooter.getSpeed() >= SPEED - 50) {
      lowerconveyor.setPower(LPOWER);
      upperconveyor.setPower(UPOWER);

      onTarget = true;
      timer.reset();
		  timer.start();
    }
    else if (onTarget && shooter.getSpeed() >= SPEED - 50) {
      lowerconveyor.setPower(LPOWER);
      upperconveyor.setPower(UPOWER);
    }
    else if (shooter.getSpeed() < SPEED - 50) {
      upperconveyor.stop();
      lowerconveyor.stop();
    }
  }
  
  /** isFinished ************************************************************	
   * Make this return true when this Command no longer needs to run execute() */
  protected boolean isFinished() {
    if (timedShoot) {
      return timer.get() >= time;
    }
    else {
      return false;
    }
  }
  
  /** end *******************************************************************
   * Called once after isFinished returns true */
  protected void end() {
    log.add("End", Log.Level.TRACE);
    upperconveyor.stop();
    lowerconveyor.stop();

    if(timedShoot){
      timer.stop();
		  timer.reset();
    }
  }

  /** interrupted ***********************************************************
   * Called when another command which requires one or more of the same subsystems is scheduled to run */
  protected void interrupted() {
    log.add("Interrupted", Log.Level.TRACE);
    upperconveyor.stop();
    lowerconveyor.stop();

    if(timedShoot){
      timer.stop();
		  timer.reset();
    }
  }
}