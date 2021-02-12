package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.autonomous.AutonomousMode;
import org.usfirst.frc.team3042.robot.commands.autonomous.AutonomousMode_Delayed;
import org.usfirst.frc.team3042.robot.commands.autonomous.AutonomousMode_Trench;
import org.usfirst.frc.team3042.robot.commands.DrivetrainAuton_Drive;
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

import org.usfirst.frc.team3042.robot.paths.*;
import org.usfirst.frc.team3042.robot.paths.PathUtil.*;
import org.usfirst.frc.team3042.lib.Path;
import java.io.*;

/** Robot *********************************************************************
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. */
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
	 * used for any initialization code. */
	public void robotInit() {
		log.add("Robot Init", Log.Level.TRACE);

		oi = new OI();

		String barrelRacingFile = "../../Pathweaver/Paths/BarrelRacingPath";
		String bounceFile = "../../Pathweaver/Paths/BouncePath";
		String slalomFile = "../../Pathweaver/Paths/SlalomPath";
		
		chooser.setDefaultOption("Default Auto", new AutonomousMode());
		chooser.addOption("Trench Six Balls", new AutonomousMode_Trench());
		chooser.addOption("Delayed Shoot", new AutonomousMode_Delayed());
		chooser.addOption("Forward 100 Inches", new DrivetrainAuton_Drive(new Forward100().buildPath()));

		buildPath("Barrel Racing", barrelRacingFile);
		buildPath("Bounce", bounceFile);
		buildPath("Slalom", slalomFile);
		
		SmartDashboard.putData("Auto Mode", chooser);

		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera1.setResolution(320, 240);
		camera1.setFPS(15);
	}

	/** disabledInit **********************************************************
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled. */
	public void disabledInit() {
		log.add("Disabled Init", Log.Level.TRACE);
		limelight.led.setNumber(1); //Turn off the Limelight's LEDs
	}

	/** disabledPeriodic ******************************************************
	 * Called repeatedly while the robot is is disabled mode. */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/** autonomousInit ********************************************************
	 * Runs once at the start of autonomous mode. */
	public void autonomousInit() {
		log.add("Autonomous Init", Log.Level.TRACE);
		ColorRecieved = false;
		SmartDashboard.putString("Color:", "Capacity Not Reached");
		
		//Don't worry about this stuff, we might use it later.

		/* String trajectoryJSON = "../../Pathweaver/output/BarrelRacingPath.wpilib.json";
		Trajectory trajectory = new Trajectory();
		try {
		  //Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
		  trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
		} catch (IOException ex) {
		  DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
		} */

		turret.reset();

		limelight.pipeline.setNumber(0); //Set the Limelight to the default pipeline
		
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/** autonomousPeriodic ****************************************************
	 * This function is called periodically during autonomous */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Shooter Speed:", shooter.getSpeed());
		SmartDashboard.putNumber("Turret Position:", turret.countsToDegrees(turret.getPosition()));
	}
	
	/** teleopInit ************************************************************
	 * This function is called when first entering teleop mode. */
	public void teleopInit() {
		log.add("Teleop Init", Log.Level.TRACE);
		ColorRecieved = false;

		stopAutonomous.start();

		limelight.pipeline.setNumber(0); //Set the Limelight to the default pipeline

		turret.reset();
		
		// This makes sure that the autonomous command stops running when teleop starts. 
		//If you want the autonomous command to continue until interrupted by another command, remove this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/** teleopPeriodic ********************************************************
	 * This function is called periodically during operator control */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Shooter Speed:", shooter.getSpeed());
		SmartDashboard.putNumber("Sensor Distance:", ultrasonicsensor.getDistance());
		SmartDashboard.putNumber("Turret Position:", turret.countsToDegrees(turret.getPosition()));
		SmartDashboard.putNumber("Drivetrain Speed", drivetrain.getEncoders().getLeftSpeed());

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

	//takes the file location of a PathWeaver file as a parameter and builds it into a drivable path
	private void buildPath(String name, String waypointFile) {
		String s;

		//TODO2-8:
		//Leave the speed like this for now -- we can get smarter later.
		double speed = 45;
		String[] splits = new String[6];

		try {
			BufferedReader br = new BufferedReader(new FileReader(waypointFile));
			//The first line of the path is not useful to us -- it has human headers. The computer doesn't need it.
			//Read one line to move the pointer forward
			br.readLine();

			//Now we need the start position so we can make the pathbuilder. Read the second line:
			s = br.readLine();

			//I want to get the x,y from the second line, so I am going to split up the line like this:
			splits = s.split(",");

			//This breaks it up into an array of strings instead based on commas.
			// "123" | "456" | "789" |
			//But this is like typing "one" instead of the number. So when I put the values into PathBuilder, I need to tell it to make it into doubles:
			double x = Double.parseDouble(splits[0]);
			double y =  Double.parseDouble(splits[1]);
			PathBuilder pb = new PathBuilder(x,y, false);

			//These are here as a hint:
			double previousTangent = 0;
			double previousX = x;

			//And here are more variables you will need
			double tangent = 0;
			double radius = 0;

			//Now, what do we do to the rest of the file to add the rest of the waypoints? 
			//We will need to track outside of just reading the line: 
			while((s = br.readLine()) != null) {
				//Here is the math part so we don't need to mess with it.
				splits = s.split(",");

				//This breaks it up into an array of strings instead based on commas.
				// "123" | "456" | "789" |
				//But this is like typing "one" instead of the number. So when I put the values into PathBuilder, I need to tell it to make it into doubles:
				x = Double.parseDouble(splits[0]) * 12; //Multiply by 12 to convert from feet to inches
				y =  Double.parseDouble(splits[1]) * 12; //Multiply by 12 to convert from feet to inches
				tangent =  Double.parseDouble(splits[4]);
				radius = (previousX-x)/(Math.cos(previousTangent - tangent));
				pb.AddWaypoint(new Waypoint(x, y, radius, speed));
				previousX = x;
				previousTangent = tangent;
			}

			//After it's all read, build:
			Path pathToDrive = pb.buildPath();
			chooser.addOption(name, new DrivetrainAuton_Drive(pathToDrive));

			//we have to close the file. it's good practice. It may automatically do it for us, but if we don't, this will only run once.
			br.close();
		} catch (IOException ex) {
			DriverStation.reportError("Unable to open file: " + waypointFile, ex.getStackTrace());
		}
	}
}