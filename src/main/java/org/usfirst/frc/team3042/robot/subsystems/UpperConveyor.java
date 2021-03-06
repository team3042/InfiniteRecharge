package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Upper Conveyor ****************************************************************
 * Subsystem for the Upper Conveyor */
public class UpperConveyor extends Subsystem {
	/** Configuration Constants **************************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
	private static final int CAN_UPPERCONVEYOR = RobotMap.CAN_UPPER_CONVEYOR;
	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_UPPER_CONVEYOR;
  	private static final NeutralMode BRAKE_MODE = RobotMap.UPPER_CONVEYOR_BRAKE_MODE;

	/** Instance Variables *******************************************************/
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	VictorSPX motor = new VictorSPX(CAN_UPPERCONVEYOR);

	/** Upper Conveyor ***********************************************************/
	public UpperConveyor() {
		log.add("Constructor", LOG_LEVEL);
		
		initMotor(motor, REVERSE_MOTOR);       
  	}

  	private void initMotor(VictorSPX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); // affects percent Vbus mode
  	}
  
 	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double Power) {
		Power = safetyCheck(Power);
				
		motor.set(ControlMode.PercentOutput, Power);		
	}
	public void stop() {
		setPower(0.0);
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem. */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}