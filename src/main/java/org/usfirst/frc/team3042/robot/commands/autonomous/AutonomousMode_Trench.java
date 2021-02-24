package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroTurn;
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
 * Drive off the initiation line, shoot the three pre-loaded balls, then drive into the trench to collect three more balls and shoot those */
public class AutonomousMode_Trench extends CommandGroup {

  public AutonomousMode_Trench() {
    /* addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addSequential(new Drivetrain_GyroStraight(12.0, 80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(3)); //Shoot the three pre-loaded power cells; parameter is time in seconds
    addSequential(new Turret_Stop()); //Stop tracking the target and running the shooter when we don't need to

    // Attempt to knock down the intake using inertia //
    addSequential(new Drivetrain_GyroStraight(18.0, 100.0)); //Drive forwards
    addSequential(new Drivetrain_GyroStraight(18.0, -100.0)); //Drive backwards */

    addParallel(new Intake_Intake(1)); //Start running the intake

    //addSequential(new DrivetrainAuton_Drive(new enterTrench().buildPath())); //Drive into the trench to collect more power cells

    //Drive into the trench to collect more power cells
    addSequential(new Drivetrain_GyroTurn(-45));
    addSequential(new Drivetrain_GyroStraight(250, 100));
    addSequential(new Drivetrain_GyroTurn(45));
    addSequential(new Drivetrain_GyroStraight(100, 100));

    addSequential(new Intake_Intake(0)); //Stop running the intake
    /* addParallel(new Turret_Continous(true)); //Search for the target and start tracking it again
    addParallel(new Shooter_Spin()); //Spin up the shooter again

    //addSequential(new DrivetrainAuton_Drive(new exitTrench().buildPath())); //Drive back to the optimal shooting location

    //Drive back to the optimal shooting location
    addSequential(new Drivetrain_GyroStraight(100, -100));
    addSequential(new Drivetrain_GyroTurn(-45));
    addSequential(new Drivetrain_GyroStraight(250, -100));
    addSequential(new Drivetrain_GyroTurn(45));

    addSequential(new Shoot()); //Shoot the three power cells from the trench */
  }
}