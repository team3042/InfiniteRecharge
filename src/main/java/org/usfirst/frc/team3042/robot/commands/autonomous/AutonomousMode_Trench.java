package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.DrivetrainAuton_Drive;
import org.usfirst.frc.team3042.robot.commands.Intake_Intake;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;
import org.usfirst.frc.team3042.robot.commands.Turret_Stop;
import org.usfirst.frc.team3042.robot.paths.enterTrench;
import org.usfirst.frc.team3042.robot.paths.exitTrench;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Trench ******************************************************
 * Drive off the initiation line, shoot the three pre-loaded balls, then drive into the trench to collect three more balls and shoot those
 */
public class AutonomousMode_Trench extends CommandGroup {

  public AutonomousMode_Trench() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(12.0, -80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(4)); //Shoot the three pre-loaded power cells; parameter is time in seconds
    addSequential(new Turret_Stop()); //Stop tracking the target when we don't need to

    // Attempt to knock down the intake using inertia //
    addSequential(new Drivetrain_GyroStraight(-18.0, -140.0)); //Drive backwards
    addSequential(new Drivetrain_GyroStraight(18.0, -140.0)); //Drive forwards 

    addParallel(new Intake_Intake(1)); //Start running the intake
    addSequential(new DrivetrainAuton_Drive(new enterTrench().buildPath())); //Drive into the trench to collect more power cells
    addSequential(new Intake_Intake(0)); //Stop running the intake
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it again
    addSequential(new DrivetrainAuton_Drive(new exitTrench().buildPath())); //Drive back to the optimal shooting location
    addSequential(new Shoot(4)); //Shoot the three power cells from the trench; parameter is time in seconds
  }
}