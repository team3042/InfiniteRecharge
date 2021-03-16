package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.drivetrain.Drivetrain_TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Drivetrain ****************************************************************
 * The drivetrain subsystem for the robot. */
public class Drivetrain extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	private static final int CAN_LEFT_MOTOR = RobotMap.CAN_LEFT_MOTOR;
	private static final int CAN_RIGHT_MOTOR = RobotMap.CAN_RIGHT_MOTOR;
	private static final NeutralMode BRAKE_MODE = RobotMap.DRIVETRAIN_BRAKE_MODE;
	private static final boolean REVERSE_LEFT_MOTOR = RobotMap.REVERSE_LEFT_MOTOR;
	private static final boolean REVERSE_RIGHT_MOTOR = RobotMap.REVERSE_RIGHT_MOTOR;	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	TalonSRX leftMotor = new TalonSRX(CAN_LEFT_MOTOR);
	TalonSRX rightMotor = new TalonSRX(CAN_RIGHT_MOTOR);
	Gyro gyroscope = new ADXRS450_Gyro(); // The gyroscope sensor

	DrivetrainFollowers followers;
	DrivetrainEncoders encoders;
	DifferentialDriveOdometry odometry; // Odometry class for tracking robot pose
	
	/** Drivetrain ************************************************************
	 * Set up the talons for desired behavior. */
	public Drivetrain() {
		log.add("Constructor", LOG_LEVEL);
		
		followers = new DrivetrainFollowers();
		encoders = new DrivetrainEncoders(leftMotor, rightMotor);
		
		initMotor(leftMotor, REVERSE_LEFT_MOTOR);
		initMotor(rightMotor, REVERSE_RIGHT_MOTOR);

		odometry = new DifferentialDriveOdometry(gyroscope.getRotation2d());
	}
	private void initMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem. */
	public void initDefaultCommand() {
		setDefaultCommand(new Drivetrain_TankDrive());
	}
	
	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double leftPower, double rightPower) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);
				
		leftMotor.set(ControlMode.PercentOutput, leftPower);
		rightMotor.set(ControlMode.PercentOutput, rightPower);		
	}
	public void stop() {
		setPower(0.0, 0.0);
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}

	/** Gyroscope/Odometry Methods *****************************/
  	public void updateOdometry() { // Updates the field-relative position.
    	odometry.update(gyroscope.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance()); //TODO: Are these encoder distances right?
  	}
  	public void resetOdometry(Pose2d pose) { // Resets the field-relative position to a specific location.
    	odometry.resetPosition(pose, gyroscope.getRotation2d());
  	}
  	public Pose2d getPose() { // Returns the position of the robot.
    	return odometry.getPoseMeters();
  	}

  	public void zeroGyro() { // Zeroes the heading of the robot
    	gyroscope.reset();
	  }
	public double getAngle() { // Returns the heading of the robot
		return gyroscope.getRotation2d().getDegrees();
	}
	public double getTurnRate() { // Returns the turn rate of the robot
		return -gyroscope.getRate();
	}
	
	/** Provide commands access to the encoders ****************/
	public DrivetrainEncoders getEncoders() {
		return encoders;
	}
}