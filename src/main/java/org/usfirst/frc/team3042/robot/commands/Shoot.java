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
import org.usfirst.frc.team3042.robot.subsystems.ShooterEncoder;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

/** Shoot *******************************************************
 * Command for pushing power cells into the shooter
 */
public class Shoot extends Command {
    /** Configuration Constants ***********************************************/
    private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
    private static final double LPOWER = RobotMap.LOWER_CONVEYOR_POWER;
    private static final double UPOWER = RobotMap.UPPER_CONVEYOR_POWER;
    private static final int SPEED = RobotMap.MIN_SHOOTER_SPEED;
    private static final double TIME = RobotMap.CONVEYOR_SHOOT_DURATION;
    private static final double TOLERANCE = RobotMap.TURRET_ANGLE_TOLERANCE * 2;

    /** Instance Variables ****************************************************/
    UpperConveyor upperconveyor = Robot.upperconveyor;
    LowerConveyor lowerconveyor = Robot.lowerconveyor;
    Shooter shooter = Robot.shooter;
    ShooterEncoder encoder = shooter.getEncoder();
    Limelight limelight = Robot.limelight;
    Log log = new Log(LOG_LEVEL, SendableRegistry.getName(upperconveyor));

    Timer timer = new Timer();
    boolean auto;
    boolean shooting = false;

    /** Shoot ***************************************************
     * Required subsystems will cancel commands when this command is run.
     */
    public Shoot(boolean autonomous) {
      log.add("Constructor", Log.Level.TRACE);
      
      auto = autonomous;

      requires(upperconveyor);
      requires(lowerconveyor);
    }

	/** initialize ************************************************************
     * Called just before this Command runs the first time
     */
    protected void initialize() {
      log.add("Initialize", Log.Level.TRACE);
      timer.reset();
    }

    /** execute ***************************************************************
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
      //if (limelight.returnValidTarget() == 1.0 && Math.abs(limelight.returnHorizontalError()) <= TOLERANCE && encoder.getSpeed() >= SPEED) {
        lowerconveyor.setPower(LPOWER);
        upperconveyor.setPower(UPOWER);
        if (!shooting && auto) {
          timer.start();
          shooting = true;
        }
      }
      /*else {
        upperconveyor.stop();
        lowerconveyor.stop();
      }*/
    //}
    
    /** isFinished ************************************************************	
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
      if (auto) {
        return timer.get() >= TIME;
      }
      else {
        return false;
      }
    }
    
    /** end *******************************************************************
     * Called once after isFinished returns true
     */
    protected void end() {
      log.add("End", Log.Level.TRACE);
      upperconveyor.stop();
      lowerconveyor.stop();
      if (auto) {
        timer.stop();
        shooting = false;
      }
    }

    /** interrupted ***********************************************************
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
      log.add("Interrupted", Log.Level.TRACE);
      upperconveyor.stop();
      lowerconveyor.stop();
      if (auto) {
        timer.stop();
        shooting = false;
      }
    }
}