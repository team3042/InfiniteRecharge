package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroTurn;
import org.usfirst.frc.team3042.robot.commands.Intake_Intake;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Trench ******************************************************
 * Drive forwards, shoot the three pre-loaded balls, drive forwards and collect three from the trench and shoot those
 */
public class AutonomousMode_Trench extends CommandGroup {

  public AutonomousMode_Trench() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(12.0, -80.0)); //Drive forwards off the initiation line
    addSequential(new Shoot()); //Shoot the three pre-loaded power cells
    addSequential(new Drivetrain_GyroTurn(90)); //Spin to knock down the intake
    addSequential(new Drivetrain_GyroTurn(-90)); //Spin to knock down the intake
    addParallel(new Intake_Intake(1)); //Start running the intake
    addSequential(new Drivetrain_GyroStraight(24.0, -40.0)); //Drive forwards into the trench
    addSequential(new Intake_Intake(0)); //Stop running the intake
    addSequential(new Drivetrain_GyroStraight(24.0, 80.0)); //Drive back to the optimal shooting location
    addSequential(new Shoot()); //Shoot the three power cells from the trench
  }
}