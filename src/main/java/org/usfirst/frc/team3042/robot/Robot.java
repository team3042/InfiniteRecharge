package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.autonomous.AutonomousMode;
import org.usfirst.frc.team3042.robot.commands.autonomous.AutonomousMode_Delayed;
import org.usfirst.frc.team3042.robot.commands.Turret_Stop;
import org.usfirst.frc.team3042.robot.subsystems.ClimbingHook;
import org.usfirst.frc.team3042.robot.subsystems.ClimbingWinch;
import org.usfirst.frc.team3042.robot.subsystems.ColorSensor;
import org.usfirst.frc.team3042.robot.subsystems.ControlPanelWheel;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3042.robot.subsystems.Gyroscope;
import org.usfirst.frc.team3042.robot.subsystems.Intake;
import org.usfirst.frc.team3042.robot.subsystems.Limelight;
import org.usfirst.frc.team3042.robot.subsystems.LowerConveyor;
import org.usfirst.frc.team3042.robot.subsystems.Shooter;
import org.usfirst.frc.team3042.robot.subsystems.Turret;
import org.usfirst.frc.team3042.robot.subsystems.UltrasonicSensor;
import org.usfirst.frc.team3042.robot.subsystems.UpperConveyor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/** Robot *********************************************************************
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot { 
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ROBOT;

	/** Create Subsystems *****************************************************/
	private Log log = new Log(LOG_LEVEL, "Robot");
	public static final Drivetrain drivetrain 			  = new Drivetrain();
	public static final Gyroscope gyroscope 	   		  = new Gyroscope();
	public static final ColorSensor colorsensor    		  = new ColorSensor();
	public static final ControlPanelWheel cpwheel  		  = new ControlPanelWheel();
	public static final Limelight limelight        		  = new Limelight();
	public static final Turret turret 			  		  = new Turret();
	public static final Intake intake 			  		  = new Intake();
	public static final Shooter shooter			   		  = new Shooter();
	public static final LowerConveyor lowerconveyor 	  = new LowerConveyor();
  	public static final UpperConveyor upperconveyor 	  = new UpperConveyor();
	public static final PowerDistributionPanel pdp		  = new PowerDistributionPanel();
	public static final ClimbingWinch climbingwinch		  = new ClimbingWinch();
	public static final ClimbingHook climbinghook		  = new ClimbingHook();
	public static final UltrasonicSensor ultrasonicsensor = new UltrasonicSensor();
	public static OI oi;
	Command autonomousCommand;
	Command stopAutonomous = new Turret_Stop();
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	UsbCamera camera1;

	public String color;
	boolean ColorRecieved = false;

	/** robotInit *************************************************************
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		log.add("Robot Init", Log.Level.TRACE);

		oi = new OI();
		chooser.setDefaultOption("Default Auto", new AutonomousMode());
		//chooser.addOption("Trench Six Balls", new AutonomousMode_Trench());
		chooser.addOption("Delayed Shoot", new AutonomousMode_Delayed());
		SmartDashboard.putData("Auto Mode", chooser);

		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(320, 240);
		camera1.setFPS(15);
	}

	/** disabledInit **********************************************************
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		log.add("Disabled Init", Log.Level.TRACE);
		limelight.led.setNumber(1); //Turn off the Limelight's LEDs
	}

	/** disabledPeriodic ******************************************************
	 * Called repeatedly while the robot is is disabled mode.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/** autonomousInit ********************************************************
	 * Runs once at the start of autonomous mode.
	 */
	public void autonomousInit() {
		log.add("Autonomous Init", Log.Level.TRACE);
		ColorRecieved = false;
		SmartDashboard.putString("Color:", "Capacity Not Reached");

		turret.reset();

		limelight.pipeline.setNumber(0); //Set the Limelight to the default pipeline
		
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/** autonomousPeriodic ****************************************************
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Shooter Speed:", shooter.getSpeed());
	}
	
	/** teleopInit ************************************************************
	 * This function is called when first entering teleop mode.
	 */
	public void teleopInit() {
		log.add("Teleop Init", Log.Level.TRACE);
		ColorRecieved = false;

		stopAutonomous.start();

		limelight.pipeline.setNumber(0); //Set the Limelight to the default pipeline

		turret.reset();
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/** teleopPeriodic ********************************************************
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Shooter Speed:", shooter.getSpeed());
		SmartDashboard.putNumber("Sensor Distance:", ultrasonicsensor.getDistance());
		SmartDashboard.putNumber("Turret Position:", turret.countsToDegrees(turret.getPosition()));

		//Read the assigned control panel color from the FMS and display it on the dashboard
		color = DriverStation.getInstance().getGameSpecificMessage();
		if (color.length() > 0) {
			ColorRecieved = true;
			switch (color.charAt(0)) {
				case 'B' :
				SmartDashboard.putString("Color:", "Blue");
				break;
				case 'G' :
				SmartDashboard.putString("Color:", "Green");
				break;
				case 'R' :
				SmartDashboard.putString("Color:", "Red");
				break;
				case 'Y' :
				SmartDashboard.putString("Color:", "Yellow");
				break;
				default :
				SmartDashboard.putString("Color:", "ERROR");
				break;
			}
		}
		else if (!ColorRecieved) {
			SmartDashboard.putString("Color:", "Capacity Not Reached");
		}
	} 

	/** testPeriodic **********************************************************
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}
}