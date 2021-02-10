package org.usfirst.frc.team3042.robot.paths;

import java.util.ArrayList;

import org.usfirst.frc.team3042.robot.paths.PathUtil.Waypoint;
import org.usfirst.frc.team3042.lib.Path;
import org.usfirst.frc.team3042.lib.math.RigidTransform2d;
import org.usfirst.frc.team3042.lib.math.Rotation2d;
import org.usfirst.frc.team3042.lib.math.Translation2d;

public class BarrelRacing implements PathContainer {
    
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(new Waypoint(4.0,-10.0,0,60));
        waypoints.add(new Waypoint(13.5,-9.0,15,60));
        waypoints.add(new Waypoint(12.5,-11.0,0,60));
        waypoints.add(new Waypoint(11.5,-10.0,0,60));
        waypoints.add(new Waypoint(20.0,-6.0,15,60));
        waypoints.add(new Waypoint(20.0,-4.0,0,60));
        waypoints.add(new Waypoint(18.0,-5.0,0,60));
        waypoints.add(new Waypoint(25.0,-11.0,15,60));
        waypoints.add(new Waypoint(26.0,-10.0,0,60));
        waypoints.add(new Waypoint(25.0,-9.0,0,60));
        waypoints.add(new Waypoint(.4942607615710384,-7.755735061305697,15,60));

        return PathUtil.buildPathFromWaypoints(waypoints);
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
