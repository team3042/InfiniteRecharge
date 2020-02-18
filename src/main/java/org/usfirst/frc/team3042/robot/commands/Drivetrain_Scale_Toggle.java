package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Drivetrain Scale Toggle
 */
public class Drivetrain_Scale_Toggle extends InstantCommand {
  /**
   * Scales up the speed of the drivetrain so the driver can traverse the field faster
   */
  public Drivetrain_Scale_Toggle() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.oi.toggleScale();
  }
}