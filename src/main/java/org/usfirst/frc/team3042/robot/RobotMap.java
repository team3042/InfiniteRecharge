package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/** RobotMap ******************************************************************
 * The robot configuration file. */
public class RobotMap {	
	/** Robot Size Parameters *************************************************
	 * The units of the wheel diameter determine the units of the position 
	 * and speed closed-loop commands. For example, if the diameter is given 
	 * in inches, position will be in inches and speed in inches per second. */
	public static final double WHEEL_DIAMETER = 6.0;
	public static final double ROBOT_WIDTH = 27.0;
	
	/** CAN ID numbers ********************************************************/
	public static final int CAN_LEFT_MOTOR 	= 29;
	public static final int CAN_RIGHT_MOTOR = 33;
	public static final int CAN_LEFT_FOLLOWER = 28;
	public static final int CAN_RIGHT_FOLLOWER = 30;
	public static final int CAN_CONTROL_PANEL_WHEEL = 14;
	public static final int CAN_TURRET = 34;
	public static final int CAN_INTAKE = 18;
	public static final int CAN_SHOOTER = 5;
	public static final int CAN_UPPER_CONVEYOR_TOP = 8;
	public static final int CAN_UPPER_CONVEYOR_BOTTOM = 25;
	public static final int CAN_LOWER_CONVEYOR_TOP = 7;
	public static final int CAN_LOWER_CONVEYOR_BOTTOM = 32;
	public static final int CAN_CLIMBING_WINCH = 4;
	public static final int CAN_CLIMBING_HOOK = 26;
	
	/** Climbing Winch Settings ***********************************************/
	public static final boolean REVERSE_CLIMBING_WINCH = false;
	public static final NeutralMode CLIMBING_WINCH_BRAKE_MODE = NeutralMode.Brake;
	public static final double CLIMBING_WINCH_POWER = 0.95; // How much power (as a %) to give the climbing winch
	
	/** Climbing Hook Settings ************************************************/
	public static final boolean REVERSE_CLIMBING_HOOK = true;
	public static final NeutralMode CLIMBING_HOOK_BRAKE_MODE = NeutralMode.Brake; 
	public static final double CLIMBING_HOOK_POWER = 0.6; // How much power (as a %) to give the climbing hook

	/** Control Panel Wheel Settings ******************************************/
	public static final boolean REVERSE_CONTROL_PANEL_WHEEL = true;
	public static final NeutralMode CPWHEEL_BRAKE_MODE = NeutralMode.Brake;
	public static final int CPWHEEL_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int CPWHEEL_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int CPWHEEL_REVOLUTIONS = 25; // Number of revolutions for wheel to spin when performing rotation control
	public static final double POSITION_CONTROL_POWER = .4; // How much power (as a %) to give the Control Panel Wheel when performing position control
	public static final double ROTATION_CONTROL_POWER = .4; // How much power (as a %) to give the Control Panel Wheel when performing rotation control
	public static final int CPWHEEL_ENCODER_FRAME_RATE = 10;
	public static final int CPWHEEL_ENCODER_COUNTS_PER_REV = 1440; // The number of encoder counts equal to one full revolution of the encoder 
	public static final boolean REVERSE_CPWHEEL_ENCODER = false;
	public static final boolean CPWHEEL_SENSOR_PHASE = false;
	
	/** Conveyor Settings ****************************************************/
	// Upper Conveyer Settings
	public static final boolean REVERSE_UPPER_CONVEYOR_TOP = true;
	public static final boolean REVERSE_UPPER_CONVEYOR_BOTTOM = false;
	public static final NeutralMode UPPER_CONVEYOR_BRAKE_MODE = NeutralMode.Brake;
	public static final double UPPER_CONVEYOR_POWER = 0.4; // How much power (as a %) to give the upper conveyor
	// Lower Conveyer Settings
	public static final boolean REVERSE_LOWER_CONVEYOR_TOP = true;
	public static final boolean REVERSE_LOWER_CONVEYOR_BOTTOM = false;
	public static final NeutralMode LOWER_CONVEYOR_BRAKE_MODE = NeutralMode.Brake;
	public static final double LOWER_CONVEYOR_POWER = 0.5; // How much power (as a %) to give the lower conveyor
	public static final double CONVEYOR_ADVANCE_DURATION = 0.4; // How long (in seconds) to run the lower conveyor when a power cell is intaked
	
	/** DIO channels **********************************************************/
	public static final int DIO_ULTRASONIC_PING = 8;
	public static final int DIO_ULTRASONIC_ECHO = 9;
	
	/** Drivetrain Settings ***************************************************/
	public static final NeutralMode DRIVETRAIN_BRAKE_MODE = NeutralMode.Brake;
	public static final boolean REVERSE_LEFT_MOTOR = true;
	public static final boolean REVERSE_RIGHT_MOTOR = false;
	// Maximum Acceleration given in power(volts?) per second
	public static final double ACCELERATION_MAX = 1.5;
	public static final double kF_DRIVE_LEFT = 0.1817180616740088;
	public static final double kF_DRIVE_RIGHT = 0.16686239968682717;
	/** Drivetrain Encoder Settings *******************************************/
	// Encoder counts per revolution
	// In quadrature mode, actual counts will be 4x the cycles; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 1440;
	// How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean SENSOR_PHASE_LEFT = false;
	public static final boolean SENSOR_PHASE_RIGHT = false;
	/** Drivetrain Autonomous Settings ****************************************/
	public static final int AUTON_PROFILE = 0;
	public static final double kP_AUTON = 0.4;
	public static final double kI_AUTON = 0.0;
	public static final double kD_AUTON = 0.8;
	public static final int I_ZONE_AUTON = 0;
	// The rate of pushing motion profile points to the talon, in ms
	public static final int AUTON_FRAME_RATE = 10;
	// Parameters for calibrating the F-gain
	public static final double AUTON_CALIBRATE_POWER = 0.5;
	public static final double AUTON_CALIBRATE_TIME = 5.0; // seconds
	public static final int AUTON_COUNT_AVERAGE = 20;
	// Parameters for motion profile driving
	public static final int AUTON_DT_MS = 30;
	public static final double AUTON_DT_SEC = (double)AUTON_DT_MS / 1000.0;
	public static final double AUTON_ACCEL_TIME = 1.0; // time in sec
	public static final double AUTON_SMOOTH_TIME = 0.1; // time in sec
	public static final double AUTON_MAX_ACCEL = 3.0; // rev per sec per sec
	public static final int AUTON_BUFFER_TRIGGER = 10;
	public static final int AUTON_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int AUTON_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int AUTON_HEADING = 0; // unimplemented feature; set to zero
	/** Drivetrain Gyro Drive Settings ****************************************/
	public static final double kP_GYRO = 0.0175;
	public static final double kI_GYRO = 0.0;
	public static final double kD_GYRO = 0.0170;
	public static final double ANGLE_TOLERANCE = 2.0;
	public static final double MAX_POWER_GYRO = 0.4;
	public static final double kI_GYRO_INTERVAL = 0.0;

	/** Gyroscope Settings ****************************************************/
	public static final double GYROSCOPE_SCALE = 1.0; // Constant for scaling values returned by the gyroscope, leave at 1.0 by default

	/** Intake Settings *******************************************************/
	public static final boolean REVERSE_INTAKE = true;
	public static final NeutralMode INTAKE_BRAKE_MODE = NeutralMode.Brake;
	public static final double INTAKE_POWER = 0.6; // How much power (as a %) to give the intake

	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = true;
	public static final double JOYSTICK_DRIVE_SCALE = -0.8;
	public static final double JOYSTICK_DRIVE_SCALE_HIGH = -1.1;
	public static final double TRIGGER_SPINNER_SCALE = 0.1;
	public static final double JOYSTICK_DEAD_ZONE = 0.0;
	
	/** Shooter Settings ******************************************************/
	public static final boolean REVERSE_SHOOTER = true; //Used to reverse the direction of the shooter motor
	public static final double SHOOTER_VELOCITY = 3750; // Shooter velocity in RPM
	public static final int SHOOTER_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int SHOOTER_PIDIDX = 0; // used for cascading PID; set to zero
	public static final double kP_SHOOTER_SPEED = 0.0; //Proportional term, tune this 2nd
	public static final double kI_SHOOTER_SPEED = 0.0; //Intergal term, most likely this won't be needed, keep at 0
	public static final double kD_SHOOTER_SPEED = 0.0; //Derivative term, tune this last
	public static final double kF_SHOOTER_SPEED = 0.030; //Feed Forward term, tune this 1st! This value alone should get you very close to your target velocity
	public static final double SHOOTER_VELOCITY_TOLERANCE = 100; // If the velocity of the shooter is within this range of the target velocity then it is okay to shoot
	public static final int SHOOTER_ENCODER_COUNTS_PER_REV = 4096; // The number of encoder counts equal to one full revolution of the encoder
	
	/** Turret Settings *******************************************************/
	public static final boolean REVERSE_TURRET = false;
	public static final NeutralMode TURRET_BRAKE_MODE = NeutralMode.Brake;
	public static final int TURRET_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int TURRET_PIDIDX = 0; // used for cascading PID; set to zero
	public static final double kP_TURRET = 0.016; // P constant for the target-tracking PID loop
	public static final double kI_TURRET = 0.004; // I constant for the target-tracking PID loop
	public static final double kD_TURRET = 0.0; // D constant for the target-tracking PID loop
	public static final double TURRET_MAX_POWER = 0.4; // The maximum power (as a %) the turret will be given when running the target-tracking PID loop
	public static final double TURRET_MANUAL_POWER = 0.2; // How much power (as a %) to give the turret when using manual control
	public static final int TURRET_MAX_ANGLE = 80; // The maximum angle the turret can turn to in either direction (to prevent tangling of wires)
	public static final double TURRET_SEARCH_POWER = 0.6; // The speed at which the turret zips around to the other side when the max angle is reached, and also the speed at which it searches for the target if the Limelight loses it
	public static final double TURRET_ANGLE_TOLERANCE = 0.25; // If the angle of error to the target is less than this value the PID Loop will not make any corrections
	public static final int TURRET_ENCODER_FRAME_RATE = 10;
	public static final int TURRET_ENCODER_COUNTS_PER_REV = 1440; // The number of encoder counts equal to one full revolution of the encoder 
	public static final boolean REVERSE_TURRET_ENCODER = false;
	public static final boolean TURRET_SENSOR_PHASE = false;
	
	/** Ultrasonic Sensor Settings ********************************************/
	public static final double POWER_CELL_DISTANCE = 8; // If the ultrasonic sensor returns a distance smaller than this (units is inches) then there is a power cell in front of it

	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= 2;

	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT 					= "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT 					= "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH 					= "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE 						= "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE 						= true;
	public static final boolean 	LOG_TO_FILE 						= false;
	public static final Log.Level 	LOG_GLOBAL 							= Log.Level.DEBUG;
	public static final Log.Level 	LOG_ROBOT 							= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 								= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 					= Log.Level.ERROR;
	public static final Log.Level	LOG_POV_BUTTON						= Log.Level.ERROR;
	/** Subsystems ************************************************************/
	public static final Log.Level	LOG_DRIVETRAIN						= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS			= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 			= Log.Level.DEBUG;
	public static final Log.Level	LOG_DRIVETRAIN_AUTON				= Log.Level.DEBUG;
	public static final Log.Level	LOG_GYROSCOPE						= Log.Level.DEBUG;
	public static final Log.Level	LOG_COLOR_SENSOR					= Log.Level.DEBUG;
	public static final Log.Level	LOG_CONTROL_PANEL_WHEEL				= Log.Level.DEBUG;
	public static final Log.Level	LOG_LIMELIGHT						= Log.Level.DEBUG;
	public static final Log.Level	LOG_TURRET							= Log.Level.DEBUG;
	public static final Log.Level	LOG_INTAKE							= Log.Level.DEBUG;
	public static final Log.Level	LOG_SHOOTER							= Log.Level.DEBUG;
	public static final Log.Level	LOG_LOWER_CONVEYOR					= Log.Level.DEBUG;
	public static final Log.Level	LOG_UPPER_CONVEYOR					= Log.Level.DEBUG;
	public static final Log.Level	LOG_CLIMBING_WINCH					= Log.Level.DEBUG;
	public static final Log.Level	LOG_CLIMBING_HOOK					= Log.Level.DEBUG;
	public static final Log.Level	LOG_ULTRASONIC_SENSOR				= Log.Level.DEBUG;
}