package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Trench ******************************************************
 * Drive forwards, shoot the three pre-loaded balls, drive forwards and collect three from the trench and shoot those
 */
public class AutonomousMode_Trench extends CommandGroup {

  public AutonomousMode_Trench() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous()); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(10.0, 80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(true)); //Shoot the three pre-loaded power cells
    addSequential(new Drivetrain_GyroStraight(4.0, -160.0));
    addSequential(new Drivetrain_GyroStraight(4.0, 80.0));
    addParallel(new Intake_Intake(1)); //Start running the intake
    addSequential(new Drivetrain_GyroStraight(20.0, 80.0)); //Drive forwards into the trench
    addSequential(new Intake_Stop()); //Stop running the intake
    addSequential(new Shoot(true)); //Shoot the three power cells from the trench
    addSequential(new Turret_Stop()); //Stop tracking the target, stop spinning the shooter, and turn off the Limelight's LEDs
  }
}