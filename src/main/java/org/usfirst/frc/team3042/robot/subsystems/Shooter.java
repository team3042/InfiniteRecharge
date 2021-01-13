package org.usfirst.frc.team3042.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Shooter *******************************************************************
 * Subsystem for the Shooter
 */
public class Shooter extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SHOOTER;
	private static final int CAN_SHOOTER = RobotMap.CAN_SHOOTER;
	private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_SHOOTER;
	private static final double kP = RobotMap.kP_SHOOTER_SPEED;
	private static final double kI = RobotMap.kI_SHOOTER_SPEED;
	private static final double kD = RobotMap.kD_SHOOTER_SPEED;
	private static final double kFF = RobotMap.kF_SHOOTER_SPEED;
	private static final int kIz = RobotMap.I_ZONE_SHOOTER_SPEED;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	public CANSparkMax motor = new CANSparkMax(CAN_SHOOTER, MotorType.kBrushless); //initialize motor
	CANEncoder encoder;
	CANEncoder followerEncoder;
	CANPIDController pidController;
	CANPIDController followerPidController;

	/** Shooter ***************************************************************/
	public Shooter() {
		log.add("Constructor", LOG_LEVEL);

		/**In order to use PID functionality for a controller, a CANPIDController object
		 * is constructed by calling the getPIDController() method on an existing
		 * CANSparkMax object
		 */
		pidController = motor.getPIDController();

		// Encoder object created to display position values
		encoder = motor.getEncoder();

		initMotor(motor, REVERSE_MOTOR);

		// set PID coefficients
		setPID(motor);
	}

	private void initMotor(CANSparkMax motor, boolean reverse) {
		motor.setInverted(reverse); 	// affects percent Vbus mode
	}

	// set PID coefficients
 	private void setPID(CANSparkMax motor) {
		pidController.setP(kP);
		pidController.setI(kI);
		pidController.setD(kD);
		pidController.setIZone(kIz);
		pidController.setFF(kFF);
		pidController.setOutputRange(-1, 1);
  }
  
  /** Closed-Loop Control *****************************************************
	* Input units for speed is RPM*/
  	public void setSpeed(double rpm) {
		pidController.setReference(rpm, ControlType.kVelocity);
	}
	public void stop() {
		pidController.setReference(0.0, ControlType.kVelocity);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}