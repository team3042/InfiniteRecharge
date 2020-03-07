package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Shooter ****************************************************************
 * Subsystem for the Shooter
 */
public class Shooter extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER;
  	private static final int CAN_SHOOTER = RobotMap.CAN_SHOOTER;
  	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_SHOOTER;
  	private static final NeutralMode BRAKE_MODE = RobotMap.SHOOTER_BRAKE_MODE;
	private static final boolean HAS_ENCODER = RobotMap.HAS_SHOOTER_ENCODER;
	private static final double POWER = RobotMap.SHOOTER_VELOCITY;

	private static final int SPEED_PROFILE = RobotMap.SHOOTER_SPEED_PROFILE;
	private static final double kP_SPEED = RobotMap.kP_SHOOTER_SPEED;
	private static final double kI_SPEED = RobotMap.kI_SHOOTER_SPEED;
	private static final double kD_SPEED = RobotMap.kD_SHOOTER_SPEED;
	private static final double kF_SPEED = RobotMap.kF_SHOOTER_SPEED;
	private static final int I_ZONE_SPEED = RobotMap.I_ZONE_SHOOTER_SPEED;
	private static final double COUNTS_PER_REV = RobotMap.SHOOTER_ENCODER_COUNTS_PER_REV;
	private static final int TIMEOUT = RobotMap.SHOOTER_TIMEOUT;
	private static final int PIDIDX = RobotMap.SHOOTER_PIDIDX;
	//private static final int CRUISE = RobotMap.SHOOTER_CRUISE;
	//private static final int ACCEL = RobotMap.SHOOTER_ACCEL;

	/** Instance Variables ****************************************************/
  	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  	public TalonSRX motor = new TalonSRX(CAN_SHOOTER);
	  ShooterEncoder encoder;
	  public boolean spinning = false;

	/** Shooter ******************************************************/
	public Shooter() {
		log.add("Constructor", LOG_LEVEL);
		
			if (HAS_ENCODER) {
				encoder = new ShooterEncoder(motor);
			}

		initMotor(motor, REVERSE_MOTOR);

		motor.config_kP(SPEED_PROFILE, kP_SPEED, TIMEOUT);
		motor.config_kI(SPEED_PROFILE, kI_SPEED, TIMEOUT);
		motor.config_kD(SPEED_PROFILE, kD_SPEED, TIMEOUT);
		motor.config_kF(SPEED_PROFILE, kF_SPEED, TIMEOUT);
		motor.config_IntegralZone(SPEED_PROFILE, I_ZONE_SPEED, TIMEOUT);
	}

 	private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
  }
  
  /** Methods for setting the motor in Percent Vbus mode ********************/
  	public void setSpeed(double rpm) {
		log.add("Speed", rpm, LOG_LEVEL);

		if (rpm == 0) {
			spinning = false;
		}
		else if (rpm != 0) {
			spinning = true;
		}
		
		double cp100ms = rpmToCp100ms(rpm);

		motor.selectProfileSlot(SPEED_PROFILE, PIDIDX);
		motor.set(ControlMode.Velocity, cp100ms);
	}
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
	private double rpmToCp100ms(double rpm) {
		double cp100ms = rpm * (double)COUNTS_PER_REV / 600.0;
		return cp100ms;
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
  	/** Command Methods *******************************************************/
  	/** Provide commands access to the encoder ********************************/
	public ShooterEncoder getEncoder() {
		return encoder;
	}
}