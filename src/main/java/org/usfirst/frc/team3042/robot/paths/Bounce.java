package org.usfirst.frc.team3042.robot.paths;

import java.util.ArrayList;

import org.usfirst.frc.team3042.robot.paths.PathBuilder.Waypoint;
import org.usfirst.frc.team3042.lib.Path;
import org.usfirst.frc.team3042.lib.math.RigidTransform2d;
import org.usfirst.frc.team3042.lib.math.Rotation2d;
import org.usfirst.frc.team3042.lib.math.Translation2d;

public class Bounce implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(2.5,-7.5,0,60));
        waypoints.add(new Waypoint(7.5,-2.5,15,60));
        waypoints.add(new Waypoint(11.73,-11.27,0,60));
        waypoints.add(new Waypoint(15.0,-2.5,0,60));
        waypoints.add(new Waypoint(16.73,-10.77,15,60));
        waypoints.add(new Waypoint(20.0,-10.77,0,60));
        waypoints.add(new Waypoint(22.5,-2.5,0,60));
        waypoints.add(new Waypoint(27.5,-7.5,15,60));

        return PathBuilder.buildPathFromWaypoints(waypoints);
    }
    
    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(230, 242), Rotation2d.fromDegrees(180.0)); 
    }

    @Override
    public boolean isReversed() {
        return false; 
    }
}
