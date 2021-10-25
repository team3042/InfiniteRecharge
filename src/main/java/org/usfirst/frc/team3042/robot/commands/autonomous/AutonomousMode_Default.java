package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.drivetrain.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;
import org.usfirst.frc.team3042.robot.commands.autonomous.helperCommands.StopShooting;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode Default ******************************************************
 * Our default autonomous mode that drives forwards off the initiation line and then shoots the three pre-loaded power cells */
public class AutonomousMode_Default extends CommandGroup {

  public AutonomousMode_Default() {
    addParallel(new Shooter_Spin()); //Spin up the shooter
    addParallel(new Turret_Continous(true)); //Search for the target and start tracking it
    addSequential(new Drivetrain_GyroStraight(18.0, 120.0)); //Drive forwards off the initiation line
    addSequential(new Shoot(5)); //Shoot the three pre-loaded power cells; Parameter is time in seconds
    addSequential(new StopShooting()); //Turn of the turret and shooter so we don't waste battery or overheat
  }
}