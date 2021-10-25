package org.usfirst.frc.team3042.robot.commands.autonomous.helperCommands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.Turret;

/** Stop Shooting ***************************************************************
 * Command for stopping the turret and shooter at the end of an autonomous routine */
public class StopShooting extends Command {

	Turret turret = Robot.turret;
	Shooter shooter = Robot.shooter;
	Limelight limelight = Robot.limelight;
	
	public StopShooting() {
		requires(turret);
		requires(shooter);
	}

	protected void initialize() {
		limelight.led.setNumber(0); //Turn off the Limelight's LEDs
		turret.stop(); //Stop the turret
		shooter.stop(); //Stop the shooter
	}

	protected void execute() {}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}

	protected void interrupted() {}
}