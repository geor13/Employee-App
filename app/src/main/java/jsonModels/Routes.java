package jsonModels;

import java.util.ArrayList;

public class Routes {
    private Bounds bounds;

    private String copyrights;

    private ArrayList<Leg> legs;

    private OverviewPolyline overview_polyline;

    private String summary;

    private ArrayList<String> warnings;

    private ArrayList<String> waypoint_order;

    public Bounds getBounds() {
        return bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public OverviewPolyline getOverview_polyline() {
        return overview_polyline;
    }

    public String getSummary() {
        return summary;
    }

    public ArrayList<String> getWarnings() {
        return warnings;
    }

    public ArrayList<String> getWaypoint_order() {
        return waypoint_order;
    }
}
