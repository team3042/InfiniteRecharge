package org.usfirst.frc.team3042.robot.commands.drivetrain;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class Drivetrain_Trajectory extends Command {

  // The trajectory to follow.
  private Trajectory trajectory;
  
  // The Ramsete Controller to follow the trajectory.
  private final RamseteController ramseteController = new RamseteController();

  // The timer to use during the autonomous period.
  private Timer time;

  Drivetrain drivetrain = Robot.drivetrain;

  public Drivetrain_Trajectory(Trajectory path) {
    trajectory = path;
    requires(drivetrain);
  }

  @Override
  protected void initialize() {
    // Initialize the timer.
    time = new Timer();
    time.start();
 
    // Reset the drivetrain's odometry to the starting pose of the trajectory.
    drivetrain.resetOdometry(trajectory.getInitialPose());
  }

  @Override
  protected void execute() {
    // Update odometry.
    drivetrain.updateOdometry();

    // Get the desired pose from the trajectory.
    Trajectory.State desiredPose = trajectory.sample(time.get());
 
    // Get the reference chassis speeds from the Ramsete controller.
    ChassisSpeeds adjustedSpeeds = ramseteController.calculate(drivetrain.getPose(), desiredPose);
 
    // Set the linear and angular speeds.
    drivetrain.drive(adjustedSpeeds.vxMetersPerSecond, adjustedSpeeds.omegaRadiansPerSecond);
  }

  @Override
  protected boolean isFinished() {
    return time.get() >= trajectory.getTotalTimeSeconds();
  }

  @Override
  protected void end() {
    drivetrain.stop();
  }

  @Override
  protected void interrupted() {
    drivetrain.stop();
  }
}