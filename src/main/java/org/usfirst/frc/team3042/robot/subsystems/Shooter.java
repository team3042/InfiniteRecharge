package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Shooter *******************************************************************
 * Subsystem for the Shooter */
public class Shooter extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER;
	private static final int CAN_SHOOTER = RobotMap.CAN_SHOOTER;
	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_SHOOTER;
	private static final double kP = RobotMap.kP_SHOOTER_SPEED;
	private static final double kI = RobotMap.kI_SHOOTER_SPEED;
	private static final double kD = RobotMap.kD_SHOOTER_SPEED;
	private static final double kF = RobotMap.kF_SHOOTER_SPEED;
	private static final int kPIDLoopIdx = RobotMap.SHOOTER_PIDIDX;
	private static final int kTimeoutMs = RobotMap.SHOOTER_TIMEOUT;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	public TalonSRX motor = new WPI_TalonSRX(CAN_SHOOTER); //initialize motor

	/** Shooter ***************************************************************/
	public Shooter() {
		log.add("Constructor", LOG_LEVEL);

		/* Factory Default all hardware to prevent unexpected behaviour */
		motor.configFactoryDefault();
		
		/* Config sensor used for Velocity control */
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		
		/*Phase sensor accordingly: Positive/Forward Sensor Reading should match Green (blinking) Leds on Talon*/
		motor.setSensorPhase(true);

		motor.setInverted(REVERSE_MOTOR); //Reverse motor if needed

		//Set minimum and maximum output values
		motor.configNominalOutputForward(0, kTimeoutMs);
		motor.configNominalOutputReverse(0, kTimeoutMs);
		motor.configPeakOutputForward(1, kTimeoutMs);
		motor.configPeakOutputReverse(-1, kTimeoutMs);

		// set PID coefficients
		motor.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
		motor.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		motor.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
		motor.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
	}
  
    /** Velocity Control *****************************************************/
  	public void setSpeed(double rpm) {
		/*** Convert specified RPM to encoder units per 100ms ***/
		double targetVelocity_UnitsPer100ms = rpm * 4096 / 600;

		motor.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
	}
	public double getSpeed() {
		double targetVelocity_UnitsPer100ms = motor.getSelectedSensorVelocity(kPIDLoopIdx);

		return targetVelocity_UnitsPer100ms * 600 / 4096;
	}

    /** % Power Control ********************************************************/
	public void setPower(double percentPower) {
		motor.set(ControlMode.PercentOutput, percentPower);
	}
	public void stop() {
		motor.set(ControlMode.PercentOutput, 0.0);
	}
	
	/** initDefaultCommand *****************************************************/
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}