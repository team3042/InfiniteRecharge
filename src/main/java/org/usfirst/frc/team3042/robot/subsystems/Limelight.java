package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Limelight *****************************************************************
 * Subsystem for the Limelight camera */
public class Limelight extends Subsystem {
	/** Configuration Constants ***********************************************/
  	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIMELIGHT;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	NetworkTableEntry tv = table.getEntry("tv");  
	public NetworkTableEntry pipeline = table.getEntry("pipeline");
	public NetworkTableEntry led = table.getEntry("ledMode");
	
	/** Limelight ************************************************************/
	public Limelight() {
		log.add("Constructor", LOG_LEVEL);
		pipeline.setNumber(0);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem. */
	public void initDefaultCommand() {
		setDefaultCommand(null); 
	}
	
	/** Command Methods *******************************************************/
	public double returnHorizontalError() {
		double x = tx.getDouble(0.0);
		return x;
	}
	public double returnVerticalError() {
		double y = ty.getDouble(0.0);
		return y;
	}
	public double returnTargetArea() {
		double area = ta.getDouble(0.0);
		return area;
	}
	public double returnValidTarget() {
		double target = tv.getDouble(0.0);
		return target;
	}
}