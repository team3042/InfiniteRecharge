package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import org.usfirst.frc.team3042.robot.commands.Turret_Manual;
import org.usfirst.frc.team3042.robot.commands.UpperConveyor_Test;
import org.usfirst.frc.team3042.robot.commands.ClimbingHook_Manual;
import org.usfirst.frc.team3042.robot.commands.ClimbingWinch_Wind;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_Scale_Toggle;
import org.usfirst.frc.team3042.robot.commands.Intake_Intake;
import org.usfirst.frc.team3042.robot.commands.LowerConveyor_Test;
import org.usfirst.frc.team3042.robot.commands.PositionControl_Manual;
import org.usfirst.frc.team3042.robot.commands.RotationControl;
import org.usfirst.frc.team3042.robot.commands.Shoot;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spin;
import org.usfirst.frc.team3042.robot.commands.Shooter_Spit;
import org.usfirst.frc.team3042.robot.commands.Turret_Continous;

/** OI ************************************************************************
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {	
	/** Configuration Constants ***********************************************/
	private static final int USB_GAMEPAD = RobotMap.USB_GAMEPAD;
	private static final int USB_JOY_LEFT = RobotMap.USB_JOYSTICK_LEFT;
	private static final int USB_JOY_RIGHT = RobotMap.USB_JOYSTICK_RIGHT;
	private static final double JOYSTICK_DRIVE_SCALE = RobotMap.JOYSTICK_DRIVE_SCALE;
	private static final double JOYSTICK_DEAD_ZONE = RobotMap.JOYSTICK_DEAD_ZONE;
	private static final double TRIGGER_SPINNER_SCALE = RobotMap.TRIGGER_SPINNER_SCALE;
	private static final int JOYSTICK_Y_AXIS = Gamepad.JOY_Y_AXIS;
	private static final int GAMEPAD_LEFT_TRIGGER = Gamepad.LEFT_TRIGGER;
	private static final int GAMEPAD_RIGHT_TRIGGER = Gamepad.RIGHT_TRIGGER;
	private static final double JOYSTICK_DRIVE_SCALE_LOW = RobotMap.JOYSTICK_DRIVE_SCALE_LOW;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(RobotMap.LOG_OI, "OI");
	public Gamepad gamepad, joyLeft, joyRight;
	int driveAxisLeft, driveAxisRight;
	public static double CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
	public static boolean isLowScale = false;

	/** OI ********************************************************************
	 * Assign commands to the buttons and triggers*/
	public OI() {
		log.add("OI Constructor", Log.Level.TRACE);
		
		gamepad = new Gamepad(USB_GAMEPAD);
		
		/** Setup Driving Controls ********************************************/
		joyLeft = new Gamepad(USB_JOY_LEFT);
		joyRight = new Gamepad(USB_JOY_RIGHT);
		driveAxisLeft = JOYSTICK_Y_AXIS;
		driveAxisRight = JOYSTICK_Y_AXIS;
		
		/** Controls **********************************************************/
		//Drivetrain Controls
		joyLeft.button1.whenPressed(new Drivetrain_Scale_Toggle());
		joyLeft.button1.whenReleased(new Drivetrain_Scale_Toggle());

		//Control Panel Controls
		gamepad.A.whenPressed(new RotationControl());
		gamepad.B.whileHeld(new PositionControl_Manual());

		//Intake Controls
		gamepad.LB.whileHeld(new Intake_Intake(1));
		gamepad.LT.whileActive(new Intake_Intake(-1));
		gamepad.RT.whileActive(new UpperConveyor_Test(-1));
		gamepad.RT.whileActive(new LowerConveyor_Test(-1));
		gamepad.Y.whileHeld(new UpperConveyor_Test(1));

		//Turret Controls
		gamepad.POVRight.whileActive(new Turret_Manual(1));
		gamepad.POVLeft.whileActive(new Turret_Manual(-1));

		//Shooting Controls
		gamepad.RB.whileHeld(new Turret_Continous(false));
		gamepad.RB.whileHeld(new Shooter_Spin());
		gamepad.RB.whileActive(new Shoot());
		gamepad.X.whileHeld(new Shooter_Spit());

		//Climbing Controls
		gamepad.POVUp.whileActive(new ClimbingHook_Manual(1)); 
		gamepad.POVDown.whileActive(new ClimbingHook_Manual(-1));
		gamepad.Start.whileHeld(new ClimbingWinch_Wind(1));
	}
	
	/** Access to the driving axes values *****************************
	 * A negative has been added to make pushing forward positive.
	 */
	public double getDriveLeft() {
		double joystickValue = joyLeft.getRawAxis(driveAxisLeft);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	public double getDriveRight() {
		double joystickValue = joyRight.getRawAxis(driveAxisRight);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		joystickValue *= CURRENT_DRIVE_SCALE;
		joystickValue *= -1.0;
		return joystickValue;
	}
	public void setNormalScale() {
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
    	isLowScale = false;
    }
    public void setLowScale() {
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE_LOW;
    	isLowScale = true;
    }
    public void toggleScale(){
    	if (isLowScale) {
    		setNormalScale();
    	}
    	else {
    		setLowScale();
		}
	}	
	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < JOYSTICK_DEAD_ZONE) {
			joystickValue = 0.0;
		}
		return joystickValue;
	}
	
	/** Access the POV value *******************************************/
	public int getPOV() {
		return gamepad.getPOV();
	}
	
	/** Access the Trigger Values **************************************/
	public double getTriggerDifference() {
		double leftTrigger = gamepad.getRawAxis(GAMEPAD_LEFT_TRIGGER);
		double rightTrigger = gamepad.getRawAxis(GAMEPAD_RIGHT_TRIGGER);
		return (rightTrigger - leftTrigger) * TRIGGER_SPINNER_SCALE;
	}
}