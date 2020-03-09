package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Trench ******************************************************
 * Drive forwards, shoot the three pre-loaded balls, drive forwards and collect three from the trench and shoot those
 */
public class AutonomousMode_Trench extends CommandGroup {

  public AutonomousMode_Trench() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(15.0, -80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot()); //Shoot the three pre-loaded power cells
    addSequential(new Drivetrain_GyroTurn(90)); //Spin to knock down the intake
    addSequential(new Drivetrain_GyroTurn(-90)); //Spin to knock down the intake
    addParallel(new Intake_Intake(1)); //Start running the intake
    addSequential(new Drivetrain_GyroStraight(20.0, -40.0)); //Drive forwards into the trench
    addSequential(new Intake_Stop()); //Stop running the intake
    addSequential(new Shoot()); //Shoot the three power cells from the trench
  }
}