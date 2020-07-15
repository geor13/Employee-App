package jsonModels;

import java.util.ArrayList;

public class Photo {
    private int height;
    private ArrayList<String> html_attributions;
    private String photo_reference;
    private int width;

    public int getHeight() {
        return height;
    }

    public ArrayList<String> getHtml_attributions() {
        return html_attributions;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public int getWidth() {
        return width;
    }
}
