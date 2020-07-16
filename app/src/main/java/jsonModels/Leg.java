package jsonModels;

import java.util.ArrayList;

public class Leg {

    private Distance distance;

    private Duration duration;

    private String end_address;

    private EndLocation end_location;

    private String start_address;

    private StartLocation start_location;

    private ArrayList<Step> steps;

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getEnd_address() {
        return end_address;
    }

    public EndLocation getEnd_location() {
        return end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public StartLocation getStart_location() {
        return start_location;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
}
