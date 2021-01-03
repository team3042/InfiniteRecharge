package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Turret ********************************************************************
 * Subsystem for the rotation of the turret
 */
public class Turret extends Subsystem {
  	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_TURRET;
  	private static final int CAN_TURRET = RobotMap.CAN_TURRET;
  	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_TURRET;
  	private static final NeutralMode BRAKE_MODE = RobotMap.TURRET_BRAKE_MODE;
	private static final int FRAME_RATE = RobotMap.TURRET_ENCODER_FRAME_RATE;
	private static final int COUNTS_PER_REV = RobotMap.TURRET_ENCODER_COUNTS_PER_REV;
	private static final int TIMEOUT = RobotMap.TURRET_TIMEOUT;
	private static final int PIDIDX = RobotMap.TURRET_PIDIDX;
	private static final boolean SENSOR_PHASE = RobotMap.TURRET_SENSOR_PHASE;

	/** Instance Variables ****************************************************/
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	TalonSRX motor = new TalonSRX(CAN_TURRET);
	double positionZero;

	/** Turret ****************************************************************/
	public Turret() {
    	log.add("Constructor", LOG_LEVEL);

    	motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PIDIDX, TIMEOUT);
		motor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, FRAME_RATE, TIMEOUT);
		motor.setSensorPhase(SENSOR_PHASE); 	// affects closed-loop mode
    
		initMotor(motor, REVERSE_MOTOR);
		reset();
  	}

  	private void initMotor(TalonSRX motor, boolean reverse) {
  		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  	}
  
  	/** Methods for setting the motor in Percent Vbus mode ********************/
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

	/** Reset the encoder zero position ***************************************/
	public void reset() {
		positionZero = motor.getSelectedSensorPosition(PIDIDX);
	}

	/** Get the encoder position and velocity *********************************
	 * Encoder position returns counts
	 * Encoder speed returns counts per 100 ms, convert to RPM for output
	 */
	public double getPosition() {
		return (motor.getSelectedSensorPosition(PIDIDX) - positionZero);
	}
	public double getSpeed() {
		int cp100ms = motor.getSelectedSensorVelocity(PIDIDX);
		
		return (double)cp100ms * 10.0 * 60.0 / COUNTS_PER_REV;
	}
	public double getPositionZero() {
		return positionZero;
	}

	//Convert turret encoder counts to turret degrees
	public double countsToDegrees(double counts) {
		double degrees = counts / COUNTS_PER_REV * 360;
		return degrees;
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null); 
	}
}