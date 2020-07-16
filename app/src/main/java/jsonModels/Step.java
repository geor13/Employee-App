package jsonModels;

public class Step {

    private Distance distance;
    private Duration duration;
    private EndLocation end_location;
    private String html_instructions;
    private ThisPolyline polyline;
    private StartLocation start_location;
    private String travel_mode;

    public Distance getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public EndLocation getEnd_location() {
        return end_location;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public ThisPolyline getPolyline() {
        return polyline;
    }

    public StartLocation getStart_location() {
        return start_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }
}
