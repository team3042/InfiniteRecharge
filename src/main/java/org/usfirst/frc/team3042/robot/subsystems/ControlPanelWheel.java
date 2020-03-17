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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Control Panel Wheel ****************************************************************
 * Subsystem for the wheel that spins control panel
 */
public class ControlPanelWheel extends Subsystem {
	/** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_CONTROL_PANEL_WHEEL;
  private static final int CAN_CPWHEEL = RobotMap.CAN_CONTROL_PANEL_WHEEL;
  private static final boolean REVERSE_MOTOR = RobotMap.REVERSE_CONTROL_PANEL_WHEEL;
  private static final NeutralMode BRAKE_MODE = RobotMap.CPWHEEL_BRAKE_MODE;
  private static final int FRAME_RATE = RobotMap.CPWHEEL_ENCODER_FRAME_RATE;
	private static final int COUNTS_PER_REV = RobotMap.CPWHEEL_ENCODER_COUNTS_PER_REV;
	private static final int TIMEOUT = RobotMap.CPWHEEL_TIMEOUT;
	private static final int PIDIDX = RobotMap.CPWHEEL_PIDIDX;
	private static final boolean SENSOR_PHASE = RobotMap.CPWHEEL_SENSOR_PHASE;

	/** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  TalonSRX motor = new TalonSRX(CAN_CPWHEEL);
  private String assignedColor;
  double positionZero;

	/** Contrtol Panel Wheel ******************************************************/
	public ControlPanelWheel() {
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
  
  /** Reset the encoder zero position ****************************************/
	public void reset() {
		int counts = motor.getSelectedSensorPosition(PIDIDX);
		positionZero = countsToRev(counts);
  }
  
  /** Get the encoder position and velocity *********************************
	 * Encoder position returns counts, convert to revolutions for output
	 * Encoder speed returns counts per 100 ms, convert to RPM for output
	 */
	public double getPosition() {
		int counts = motor.getSelectedSensorPosition(PIDIDX);
		return countsToRev(counts) - positionZero;
	}
	private double countsToRev(int counts) {
		return (double)counts / COUNTS_PER_REV;
	}
	public double getSpeed() {
		int cp100ms = motor.getSelectedSensorVelocity(PIDIDX);
		
		return (double)cp100ms * 10.0 * 60.0 / COUNTS_PER_REV;
	}
	public double getPositionZero() {
		return positionZero;
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
	
  /** Command Methods *******************************************************/
  //Determines the target color for the color sensor based on the color recieved by the FMS and returns it as a string
  public String getTargetColor() {
    assignedColor = SmartDashboard.getString("Color:", "NO ASSIGNED COLOR");
    if(assignedColor.equals("Blue")) {
      return "Red";
    }
    else if(assignedColor.equals("Red")) {
      return "Blue";
    }
    else if(assignedColor.equals("Yellow")) {
      return "Green";
    }
    else if(assignedColor.equals("Green")) {
      return "Yellow";
    }
    else {
      return "UNKNOWN COLOR";
    }
  }
}