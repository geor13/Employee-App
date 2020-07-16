package jsonModels;

import java.util.ArrayList;

public class RoutesResults {

    private ArrayList<GeocodedWaypoints> geocoded_waypoints;
    private ArrayList<Routes> routes;

    public ArrayList<GeocodedWaypoints> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public ArrayList<Routes> getRoutes() {
        return routes;
    }
}
