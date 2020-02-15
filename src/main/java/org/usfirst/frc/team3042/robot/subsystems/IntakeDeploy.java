package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Intake Deploy ****************************************************************
 * Subsystem for deploying the intake
 */
public class IntakeDeploy extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_INTAKE_DEPLOY;
	//private static final int ID_R = RobotMap.INTAKE_DEPLOY_SOLENOID_RIGHT;
	//private static final int ID_L = RobotMap.INTAKE_DEPLOY_SOLENOID_LEFT;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	//Solenoid intakeDeploySolenoidR = new Solenoid(ID_R);
	//Solenoid intakeDeploySolenoidL = new Solenoid(ID_L);

	/** Intake Deploy ******************************************************/
	public IntakeDeploy() {
    	log.add("Constructor", LOG_LEVEL);
	}

	public void extend(){
		//intakeDeploySolenoidR.set(true);
		//intakeDeploySolenoidL.set(true);
	}
	public void retract() {
		//intakeDeploySolenoidR.set(false);
		//intakeDeploySolenoidL.set(false);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}