package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.LowerConveyor_Advance;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** LowerConveyor *************************************************************
 * Subsystem for the Lower Conveyor that brings the power cells to the upper conveyor */
public class LowerConveyor extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LOWER_CONVEYOR;
	private static final int CAN_LOWER_CONVEYOR = RobotMap.CAN_LOWER_CONVEYOR;
	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_LOWER_CONVEYOR;
	private static final NeutralMode BRAKE_MODE = RobotMap.LOWER_CONVEYOR_BRAKE_MODE;

	/** Instance Variables ****************************************************/
  		Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	  	VictorSPX motor = new VictorSPX(CAN_LOWER_CONVEYOR);

	/** LowerConveyor *********************************************************/
	public LowerConveyor() {
    	log.add("Constructor", LOG_LEVEL);
    
		initMotor(motor, REVERSE_MOTOR);
  	}

  	private void initMotor(VictorSPX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  	}
  
  	/** Methods for setting the motors in Percent Vbus mode *******************/
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
		setDefaultCommand(new LowerConveyor_Advance());
	}
}