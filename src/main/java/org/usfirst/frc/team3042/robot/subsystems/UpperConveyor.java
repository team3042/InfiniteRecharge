package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Upper Conveyor ****************************************************************
 * Subsystem for the Upper Conveyor
 */
public class UpperConveyor extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_UPPER_CONVEYOR;
	private static final int CAN_UPPERCONVEYOR_T = RobotMap.CAN_UPPER_CONVEYOR_TOP;
	private static final int CAN_UPPERCONVEYOR_B = RobotMap.CAN_UPPER_CONVEYOR_BOTTOM;
	private static final boolean REVERSE_MOTOR_T = RobotMap.REVERSE_UPPER_CONVEYOR_TOP;
  	private static final boolean REVERSE_MOTOR_B = RobotMap.REVERSE_UPPER_CONVEYOR_BOTTOM;
  	private static final NeutralMode BRAKE_MODE = RobotMap.UPPER_CONVEYOR_BRAKE_MODE;

	/** Instance Variables ****************************************************/
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	  TalonSRX motor = new TalonSRX(CAN_UPPERCONVEYOR_T);
	  TalonSRX motor2 = new TalonSRX(CAN_UPPERCONVEYOR_B);

	/** Upper Conveyor ******************************************************/
	public UpperConveyor() {
		log.add("Constructor", LOG_LEVEL);
		
		initMotor(motor, REVERSE_MOTOR_T);    
		initMotor(motor2, REVERSE_MOTOR_B);   
  	}

  	private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  	}
  
 	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double Power) {
		Power = safetyCheck(Power);
				
		motor.set(ControlMode.PercentOutput, Power);	
		motor2.set(ControlMode.PercentOutput, Power);	
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
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}