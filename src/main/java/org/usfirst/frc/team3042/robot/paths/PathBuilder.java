package org.usfirst.frc.team3042.robot.paths;

import java.util.*;

import org.usfirst.frc.team3042.robot.paths.PathUtil.Waypoint;
import org.usfirst.frc.team3042.lib.Path;
import org.usfirst.frc.team3042.lib.math.RigidTransform2d;
import org.usfirst.frc.team3042.lib.math.Rotation2d;
import org.usfirst.frc.team3042.lib.math.Translation2d;

public class PathBuilder implements PathContainer{
    
    private ArrayList<Waypoint> Waypoints; 
    private RigidTransform2d StartPose;
    private boolean IsReversed;
    
    public PathBuilder(RigidTransform2d startPose, boolean isReversed){
        StartPose = startPose;
        IsReversed = isReversed;
        Waypoints  = new ArrayList<Waypoint>();
    }
    public PathBuilder(double x, double y, boolean isReversed){
        //TODO 2-8: Look at the RigidTransform2d and find out how to make one from X,Y coordinates (knowing that we want the Rotation2d with angle 180).
        //Here's an example Start2d:
        //public RigidTransform2d getStartPose() {
        //return new RigidTransform2d(new Translation2d(230, 242), Rotation2d.fromDegrees(180)); 
        StartPose = new RigidTransform2d(new Translation2d(Math.floor(x), Math.floor(y)), Rotation2d.fromDegrees(180));
        IsReversed = isReversed;
        Waypoints  = new ArrayList<Waypoint>();
    }
    public void addWaypoint(Waypoint newPoint){
        
        Waypoints.add(newPoint);
    }

    public Path buildPath(){
        return PathUtil.buildPathFromWaypoints(this.Waypoints);
    }

    @Override
    public RigidTransform2d getStartPose(){return StartPose;}

    @Override
    public boolean isReversed(){return IsReversed;}
}
