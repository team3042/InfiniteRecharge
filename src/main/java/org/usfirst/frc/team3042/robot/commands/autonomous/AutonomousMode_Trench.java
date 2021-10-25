package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.drivetrain.*;
import org.usfirst.frc.team3042.robot.commands.Intake_Intake;
import org.usfirst.frc.team3042.robot.commands.LowerConveyor_Advance;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;
import org.usfirst.frc.team3042.robot.commands.autonomous.helperCommands.StopShooting;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Trench ******************************************************
 * Drive off the initiation line, shoot the three pre-loaded balls, then drive into the trench to collect three more balls and shoot those */
public class AutonomousMode_Trench extends CommandGroup {

  public AutonomousMode_Trench() {
    addParallel(new LowerConveyor_Advance()); //Call the conveyor default command since it has been interrupted by this command group

    addParallel(new Turret_Continous(true)); //Start tracking the target with the turret
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addSequential(new Drivetrain_GyroStraight(18.0, 120.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(2.6)); //Shoot the 3 pre-loaded power cells; Parameter is time in seconds
    addSequential(new StopShooting()); //Stop tracking the target and running the shooter when we don't need to

    // Knock down the intake using inertia //
    addSequential(new Drivetrain_GyroStraight(12.0, 100.0)); //Drive forwards
    addSequential(new Drivetrain_GyroStraight(12.0, -100.0)); //Drive backwards

    addParallel(new Intake_Intake(1)); //Start running the intake

    // Drive into the trench to collect 3 power cells //
    addSequential(new Drivetrain_GyroStraight(80, 220)); 

    addSequential(new Intake_Intake(0)); //Stop running the intake

    // Drive closer to the power port before shooting //
    addSequential(new Drivetrain_GyroStraight(80, -120)); 

    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Start tracking the target with the turret
    addSequential(new Shoot()); //Shoot the 3 power cells we collected from the trench
  }
}