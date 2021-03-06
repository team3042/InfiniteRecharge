package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
	private static final int kPIDLoopIdx = RobotMap.SHOOTER_PIDIDX;
	private static final int kTimeoutMs = RobotMap.SHOOTER_TIMEOUT;
	private static final int COUNTS_PER_REV = RobotMap.SHOOTER_ENCODER_COUNTS_PER_REV;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	public WPI_TalonSRX motor = new WPI_TalonSRX(CAN_SHOOTER); //initialize motor

	/** Shooter ***************************************************************/
	public Shooter() {
		log.add("Constructor", LOG_LEVEL);

		/* Factory Default all hardware to prevent unexpected behavior */
		motor.configFactoryDefault();
		
		/* Config sensor used for Velocity control */
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		
		/* Phase sensor accordingly: Positive/Forward Sensor Reading should match Green (blinking) LEDs on Talon */
		motor.setSensorPhase(true);

		motor.setInverted(REVERSE_MOTOR); //Reverse motor if needed

		motor.setNeutralMode(NeutralMode.Coast);

		//Set minimum and maximum output values
		motor.configNominalOutputForward(0, kTimeoutMs);
		motor.configNominalOutputReverse(0, kTimeoutMs);
		motor.configPeakOutputForward(1, kTimeoutMs);
		motor.configPeakOutputReverse(0, kTimeoutMs);
	}
  
    /** Velocity Control *****************************************************/
	public double getSpeed() {
		double targetVelocity_UnitsPer100ms = motor.getSelectedSensorVelocity(kPIDLoopIdx);

		return targetVelocity_UnitsPer100ms * 600 / COUNTS_PER_REV;
	}

    /** % Power Control ********************************************************/
	public void setPower(double percentPower) {
		motor.set(ControlMode.PercentOutput, percentPower);
	}
	public void stop() {
		motor.set(ControlMode.PercentOutput, 0.0);
	}

	/** Voltage Control ********************************************************/
	public void setVoltage(double volts) { //This needs to be called continiously for voltage compensation to work correctly!
		double power = Math.min(volts, 12.0); //This ensures that we never give the motor more than 12V
		motor.setVoltage(power);
	}
	
	/** initDefaultCommand *****************************************************/
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}