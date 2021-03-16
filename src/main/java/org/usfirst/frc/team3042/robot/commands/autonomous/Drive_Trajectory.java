package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class Drive_Trajectory extends Command {

   // The trajectory to follow.
   private Trajectory m_trajectory;
  
  // The Ramsete Controller to follow the trajectory.
  private final RamseteController m_ramseteController = new RamseteController();

  // The timer to use during the autonomous period.
  private Timer m_timer;

  Drivetrain drivetrain;

  public Drive_Trajectory(Trajectory path) {
    m_trajectory = path;
  }

  @Override
  protected void initialize() {
     // Initialize the timer.
     m_timer = new Timer();
     m_timer.start();
 
     // Reset the drivetrain's odometry to the starting pose of the trajectory.
     drivetrain.resetOdometry(m_trajectory.getInitialPose());
  }

  @Override
  protected void execute() {
     // Update odometry.
     drivetrain.updateOdometry();

    // Get the desired pose from the trajectory.
    var desiredPose = m_trajectory.sample(m_timer.get());
 
    // Get the reference chassis speeds from the Ramsete controller.
    var refChassisSpeeds = m_ramseteController.calculate(drivetrain.getPose(), desiredPose);
 
    // Set the linear and angular speeds.
    drivetrain.setPower(refChassisSpeeds.vxMetersPerSecond, refChassisSpeeds.omegaRadiansPerSecond);
  }

  @Override
  protected boolean isFinished() {
    return m_timer.get() >= m_trajectory.getTotalTimeSeconds();
  }

  @Override
  protected void end() {
    drivetrain.setPower(0, 0);
  }

  @Override
  protected void interrupted() {
    drivetrain.setPower(0, 0);
  }
}
