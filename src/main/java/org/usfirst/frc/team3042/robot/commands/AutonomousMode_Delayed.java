package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Delayed ******************************************************
 * Autonomous Mode for driving forwards and then shooting the three pre-loaded balls after waiting a certain number of seconds
 */
public class AutonomousMode_Delayed extends CommandGroup {

  public AutonomousMode_Delayed() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Wait(8)); //Wait 10 seconds
    addSequential(new Drivetrain_GyroStraight(12.0, -80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(true)); //Shoot the three pre-loaded power cells
    addSequential(new Turret_Stop()); //Stop tracking the target, stop spinning the shooter, and turn off the Limelight's LEDs
  }
}