package jsonModels;

import java.util.ArrayList;

public class Candidates {
    private String formatted_address;
    private Geometry geometry;
    private String name;
    private ArrayList<Photo> photos;
    private float rating;

    public String getFormatted_address() {
        return formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public float getRating() {
        return rating;
    }
}
