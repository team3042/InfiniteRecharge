package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.drivetrain.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;
import org.usfirst.frc.team3042.robot.commands.autonomous.helperCommands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Delayed ******************************************************
 * An autonomous routine for driving forwards off the initiation line and then shooting the three pre-loaded balls after waiting a specified number of seconds */
public class AutonomousMode_Delayed extends CommandGroup {

  public AutonomousMode_Delayed() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Wait(10)); //Wait 10 seconds
    addSequential(new Drivetrain_GyroStraight(18.0, 120.0)); //Drive forwards off the initiation line
    addSequential(new Shoot()); //Shoot the three pre-loaded power cells
  }
}