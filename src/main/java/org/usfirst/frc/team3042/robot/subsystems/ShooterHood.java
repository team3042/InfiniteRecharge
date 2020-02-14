package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Shooter Hood ****************************************************************
 * Subsystem for adjusting the vertical angle of the shooter hood
 */
public class ShooterHood extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER_HOOD;
	//private static final int ID = RobotMap.SHOOTER_HOOD_SOLENOID;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	//Solenoid shooterHoodSolenoid = new Solenoid(ID);

	/** ShooterHood ******************************************************/
	public ShooterHood() {
		log.add("Constructor", LOG_LEVEL);
		SmartDashboard.putString("Shooter Mode", "CLOSE");
	}

	public void extend() {
		//shooterHoodSolenoid.set(true);
	}

	public void retract() {
		//shooterHoodSolenoid.set(false);
	}
  
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}