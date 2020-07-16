package jsonModels;

import java.util.ArrayList;

public class GeocodedWaypoints {

    private String geocoder_status;
    private String place_id;
    private ArrayList<String> types;

    public String getGeocoder_status() {
        return geocoder_status;
    }

    public String getPlace_id() {
        return place_id;
    }

    public ArrayList<String> getTypes() {
        return types;
    }
}
