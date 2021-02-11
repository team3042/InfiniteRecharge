
package org.usfirst.frc.team3042.robot.commands.autonomous;

import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;
import org.usfirst.frc.team3042.robot.paths.PathBuilder;
import org.usfirst.frc.team3042.robot.paths.PathContainer;
import org.usfirst.frc.team3042.robot.commands.DrivetrainAuton_Drive;
import edu.wpi.first.wpilibj.command.CommandGroup;

/** Autonomous Mode ******************************************************
 * Autonomous Mode for driving forwards and then shooting the three pre-loaded balls
 */
public class AutonomousMode_DoPath extends CommandGroup {

  public AutonomousMode_DoPath(PathContainer pathContainer) {
    addSequential(new DrivetrainAuton_Drive(pathContainer.buildPath())); //Drive using AutonDrive
    //addSequential(new Drivetrain_GyroStraight(10, 1));
  }
}