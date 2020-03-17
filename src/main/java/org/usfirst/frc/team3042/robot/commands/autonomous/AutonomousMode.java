package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode ******************************************************
 * Autonomous Mode for driving forwards and then shooting the three pre-loaded balls
 */
public class AutonomousMode extends CommandGroup {

  public AutonomousMode() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(12.0, -80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot()); //Shoot the three pre-loaded power cells
  }
}