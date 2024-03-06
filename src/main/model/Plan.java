package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a plan having a day, location, and a description
public class Plan implements Writable {
    private String location;
    private int day;
    private String description;
    private Boolean hasVisited;

    //EFFECTS: creates an instance of Plan that has the day, location, description and not visited
    public Plan(int day, String location, String description) {
        this.day = day;
        this.location = location;
        this.description = description;
        hasVisited = false;
    }

    //EFFECTS: returns day
    public int getDay() {
        return day;
    }

    //EFFECTS: returns location
    public String getLocation() {
        return location;
    }

    //EFFECTS: returns description
    public String getDescription() {
        return description;
    }

    //EFFECTS: returns true if visited, false if
    public boolean getHasVisited() {
        return hasVisited;
    }

    //MODIFIES: this
    //EFFECTS: sets day to the new day
    public void setDay(int day) {
        this.day = day;
    }

    //MODIFIES: this
    //EFFECTS: sets location to the new location
    public void setLocation(String location) {
        this.location = location;
    }

    //MODIFIES: this
    //EFFECTS: sets description to the new description
    public void setDescription(String description) {
        this.description = description;
    }

    //MODIFIES: this
    //EFFECTS: changes the status of the plan to visited
    public void visited() {
        hasVisited = true;
    }

    //REQUIRES: hasVisited()
    //MODIFIES: this
    //EFFECTS: changes the status of the plan to visited
    public void revisit() {
        hasVisited = false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("location", location);
        json.put("description", description);
        return json;
    }

}
