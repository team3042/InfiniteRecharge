package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	/** Robot selector ********************************************************/
	public static enum Bot {PBOT, ARTEMIS;}
	// Set the bot to which you intend to push code.
	private static Bot currentBot = Bot.PBOT;

	public static final boolean IS_PBOT 	= (currentBot == Bot.PBOT);
	public static final boolean IS_ARTEMIS = (currentBot == Bot.ARTEMIS);
	
	/** Robot Size Parameters *************************************************
	 * The units of the wheel diameter determine the units of the position 
	 * and speed closed-loop commands. For example, if the diameter is given 
	 * in inches, position will be in inches and speed in inches per second.
	 */
	public static final double WHEEL_DIAMETER = 4.0;
	public static final double ROBOT_WIDTH = 15.0;
	
	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= 2;

	/** PWM ports *************************************************************/
	
	/** CAN ID numbers ********************************************************/
	public static final int CAN_LEFT_MOTOR 	= 			IS_PBOT 	? 3 : 3;
	public static final int CAN_RIGHT_MOTOR = 			IS_PBOT 	? 9 : 9;
	public static final int CAN_LEFT_FOLLOWER = 		IS_PBOT 	? 3 : 0;
	public static final int CAN_RIGHT_FOLLOWER = 		IS_PBOT 	? 9 : 0;
	public static final int CAN_CONTROL_PANEL_WHEEL = 	IS_PBOT 	? 10 : 10;
	public static final int CAN_TURRET = 		IS_PBOT		? 10 : 10; 
	public static final int CAN_INTAKE = 		IS_PBOT 	? 10 : 10;
	
	/** PCM channels **********************************************************/
	
	/** SPI ports *************************************************************/
	//note that the Gyroscope uses the myRIO Expansion Port (MXP) and is defined in the SPI class (edu.wpi.first.wpilibj.SPI)
	//notes for dummies: the MXP is the big boy smack center of the RoboRio (where the gyro ALWAYS goes);
	//see http://www.ni.com/pdf/manuals/374474a.pdf for additional info on the RoboRio
	
	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = true;
	public static final double JOYSTICK_DRIVE_SCALE = 0.5;
	public static final double TRIGGER_SPINNER_SCALE = 0.1;
	public static final double JOYSTICK_DEAD_ZONE = 0.0;

	/** Drivetrain Settings ***************************************************/
	public static final boolean HAS_DRIVETRAIN = true;
	public static final boolean HAS_FOLLOWERS = false;
	public static final NeutralMode DRIVETRAIN_BRAKE_MODE = NeutralMode.Brake;
	public static final boolean REVERSE_LEFT_MOTOR  = 	(IS_PBOT) ? true : false;
	public static final boolean REVERSE_RIGHT_MOTOR = 	(IS_PBOT) ? false: false;
	// Maximum Acceleration given in power per second
	public static final double ACCELERATION_MAX = 1.5;
	public static final double kF_DRIVE_LEFT = 	(IS_PBOT) 	?  0.1817180616740088  : 0.1817180616740088;
	public static final double kF_DRIVE_RIGHT = (IS_PBOT) 	?  0.16686239968682717 : 0.16686239968682717;
	
	/** Drivetrain Encoder Settings *******************************************/
	public static final boolean HAS_ENCODERS = true;
	//Encoder counts per revolution
	//In quadrature mode, actual counts will be 4x this; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 1440;
	//How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean SENSOR_PHASE_LEFT = 	(IS_PBOT) ? false: false;
	public static final boolean SENSOR_PHASE_RIGHT = 	(IS_PBOT) ? false: false;
	
	/** Drivetrain Autonomous Settings ****************************************/
	public static final boolean HAS_AUTON = HAS_ENCODERS;
	public static final int AUTON_PROFILE = 0;
	public static final double kP_AUTON = 		(IS_PBOT) 		? 0.4 : 0.0;
	public static final double kI_AUTON = 		(IS_PBOT) 		? 0.0 : 0.0;
	public static final double kD_AUTON = 		(IS_PBOT) 		? 0.8 : 0.0;
	public static final int I_ZONE_AUTON =		(IS_PBOT)		? 0   : 0;
	//The rate of pushing motion profile points to the talon, in ms
	public static final int AUTON_FRAME_RATE = 10;
	//Parameters for calibrating the F-gain
	public static final double AUTON_CALIBRATE_POWER = 0.5;
	public static final double AUTON_CALIBRATE_TIME = 5.0; //seconds
	public static final int AUTON_COUNT_AVERAGE = 20;
	//Parameters for motion profile driving
	public static final int AUTON_DT_MS = 30;
	public static final double AUTON_DT_SEC = (double)AUTON_DT_MS / 1000.0;
	public static final double AUTON_ACCEL_TIME = 1.0; //time in sec
	public static final double AUTON_SMOOTH_TIME = 0.1; //time in sec
	public static final double AUTON_MAX_ACCEL = 3.0; //rev per sec per sec
	public static final int AUTON_BUFFER_TRIGGER = 10;
	public static final int AUTON_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int AUTON_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int AUTON_HEADING = 0; //unimplemented feature; set to zero
	
	/** Drivetrain Gyro Drive Settings ****************************************/
	public static final double kP_GYRO = 0.0175;
	public static final double kI_GYRO = 0.0;
	public static final double kD_GYRO = 0.0170;
	public static final double ANGLE_TOLERANCE = 2.0;
	public static final double MAX_SPEED_GYRO = 0.4;
	public static final double kI_GYRO_INTERVAL = 0.0;
	
	/** Gyroscope Settings ****************************************************/
	public static final boolean HAS_GYROSCOPE = true;
	public static final double GYROSCOPE_SCALE = 1.0;

	/** Color Sensor Settings ****************************************************/
	public static final boolean HAS_COLOR_SENSOR = true;

	/** Limelight Settings ****************************************************/
	public static final boolean HAS_LIMELIGHT = true;

	/** Control Panel Wheel Settings ****************************************************/
	public static final boolean HAS_CONTROL_PANEL_WHEEL = true;
	public static final boolean REVERSE_CONTROL_PANEL_WHEEL  = 	(IS_PBOT) ? false : false;
	public static final NeutralMode CPWHEEL_BRAKE_MODE = NeutralMode.Brake;
	public static final int CPWHEEL_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int CPWHEEL_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int CPWHEEL_REVOLUTIONS = 25; //Number of revolutions for wheel to spin when performing rotation control
	public static final double POSITION_CONTROL_SPEED = .2;
	public static final double ROTATION_CONTROL_SPEED = .4;

	/** Control Panel Wheel Encoder Settings **********************************************/
	public static final boolean HAS_CONTROL_PANEL_WHEEL_ENCODER = HAS_CONTROL_PANEL_WHEEL;
	public static final int CPWHEEL_ENCODER_FRAME_RATE = 10;
	public static final int CPWHEEL_ENCODER_COUNTS_PER_REV = 4096;
	public static final boolean REVERSE_CPWHEEL_ENCODER = false;
	public static final boolean CPWHEEL_SENSOR_PHASE = false;

	/** Turret Settings ****************************************************/
	public static final boolean HAS_TURRET = true;
	public static final boolean REVERSE_TURRET  = 	(IS_PBOT) ? false : false;
	public static final NeutralMode TURRET_BRAKE_MODE = NeutralMode.Brake;
	public static final int TURRET_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int TURRET_PIDIDX = 0; // used for cascading PID; set to zero
	public static final double TURRET_SPEED = 0.1;

	/** Turret Encoder Settings **********************************************/
	public static final boolean HAS_TURRET_ENCODER = HAS_TURRET;
	public static final int TURRET_ENCODER_FRAME_RATE = 10;
	public static final int TURRET_ENCODER_COUNTS_PER_REV = 4096;
	public static final boolean REVERSE_TURRET_ENCODER = false;
	public static final boolean TURRET_SENSOR_PHASE = false;

	/** Intake Settings ****************************************************/
	public static final boolean HAS_INTAKE = true;
	public static final boolean REVERSE_INTAKE  = 	(IS_PBOT) ? false : false;
	public static final NeutralMode INTAKE_BRAKE_MODE = NeutralMode.Brake;
	public static final int INTAKE_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int INTAKE_PIDIDX = 0; // used for cascading PID; set to zero
	public static final double INTAKE_POWER = 0.33;
	
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Log.Level 	LOG_GLOBAL 					= Log.Level.DEBUG;
	public static final Log.Level 	LOG_ROBOT 					= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 						= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 			= Log.Level.ERROR;
	public static final Log.Level	LOG_POV_BUTTON				= Log.Level.ERROR;
	/** Subsystems **/
	public static final Log.Level	LOG_DRIVETRAIN						= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS			= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 			= Log.Level.DEBUG;
	public static final Log.Level	LOG_DRIVETRAIN_AUTON				= Log.Level.DEBUG;
	public static final Log.Level	LOG_GYROSCOPE						= Log.Level.DEBUG;
	public static final Log.Level	LOG_COLOR_SENSOR					= Log.Level.DEBUG;
	public static final Log.Level	LOG_CONTROL_PANEL_WHEEL				= Log.Level.DEBUG;
	public static final Log.Level	LOG_CONTROL_PANEL_WHEEL_ENCODER		= Log.Level.DEBUG;
	public static final Log.Level	LOG_LIMELIGHT						= Log.Level.DEBUG;
	public static final Log.Level	LOG_TURRET							= Log.Level.DEBUG;
	public static final Log.Level	LOG_TURRET_ENCODER					= Log.Level.DEBUG;
	public static final Log.Level	LOG_INTAKE							= Log.Level.DEBUG;
}