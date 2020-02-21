package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** AutonomousMode ******************************************************
 * Command Group for the 15 second autonomous period
 */
public class AutonomousMode extends CommandGroup {

  public AutonomousMode() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous()); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(60.0, 80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(true)); //Shoot the three pre-loaded power cells
    addSequential(new Turret_Stop()); //Stop tracking the target, stop spinning the shooter, and turn off the Limelight's LEDs
  }
}