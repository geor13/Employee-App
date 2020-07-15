package jsonModels;

import java.util.ArrayList;

public class RequestResults {


    private ArrayList<Candidates> candidates;
    private String status;

    public String getStatus() {
        return status;
    }

    public ArrayList<Candidates> getCandidates() {
        return candidates;
    }
}
