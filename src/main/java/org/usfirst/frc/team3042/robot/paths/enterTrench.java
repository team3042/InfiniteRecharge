package org.usfirst.frc.team3042.robot.paths;

import java.util.ArrayList;

import org.usfirst.frc.team3042.robot.paths.PathUtil.Waypoint;
import org.usfirst.frc.team3042.lib.Path;
import org.usfirst.frc.team3042.lib.math.RigidTransform2d;
import org.usfirst.frc.team3042.lib.math.Rotation2d;
import org.usfirst.frc.team3042.lib.math.Translation2d;

public class enterTrench implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(318,-27,0,60));
        waypoints.add(new Waypoint(221,-27,15,60));
        waypoints.add(new Waypoint(183,-66,15,60));
        waypoints.add(new Waypoint(132,-96,0,60));
        
        return PathUtil.buildPathFromWaypoints(waypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(132,-96), Rotation2d.fromDegrees(180)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
}
