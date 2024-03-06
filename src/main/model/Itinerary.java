package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// Represents an itinerary having a collection of plans
public class Itinerary implements Writable {

    private LinkedList<Plan> itinerary;
    private int numberOfDays;
    private String name;

    //EFFECTS: creates an instance of Itinerary with no plans
    public Itinerary(String name, int days) {
        this.name = name;
        numberOfDays = days;
        itinerary = new LinkedList<>();
    }

    //REQUIRES: 0 < plan.getDay() <= numberOfDays
    // MODIFIES: this
    // EFFECTS: adds Plan to the end of the queue
    public void addPlan(Plan plan) {
        itinerary.add(plan);
    }

    //REQUIRES: itinerary not empty
    //MODIFIES: this
    //EFFECTS: removes the first occurrence of the given location from the itinerary
    public boolean removePlan(String location) {
        for (Plan p : itinerary) {
            if (p.getLocation().equalsIgnoreCase(location)) {
                itinerary.remove(p);
                return true;
            }
        }
        return false;
    }

    //REQUIRES: 0 < p.getDay() <= numberOfDays
    //EFFECTS: returns itinerary according to their days
    public LinkedList<Plan> viewItinerary() {
        LinkedList<Plan> arrangedItinerary = new LinkedList<>();
        for (int i = 1; i <= numberOfDays; i++) {
            for (Plan p : itinerary) {
                if (p.getDay() == i) {
                    arrangedItinerary.add(p);
                }
            }
        }
        return arrangedItinerary;
    }


    //MODIFIES: this
    //EFFECTS: removes all plans in the itinerary
    public void resetItinerary() {
        itinerary.clear();

    }

    //EFFECTS: returns the number of plans in the itinerary
    public int getNumPlans() {
        return itinerary.size();
    }

    //EFFECTS: returns the number of days planned in the itinerary
    public int getNumberOfDays() {
        return numberOfDays;
    }

    //EFFECTS: returns the plan that is in that index
    public Plan getPlan(int index) {
        return itinerary.get(index);
    }

    //EFFECTS: returns the name of the itinerary
    public String getName() {
        return name;
    }

    //EFFECTS: returns all the plans in the itinerary
    public LinkedList<Plan> getAllPlans() {
        return itinerary;
    }

    //MODIFIES: this
    //EFFECTS: sets number of days to the new number of days
    public void setDay(int num) {
        numberOfDays = num;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("days", numberOfDays);
        json.put("plans", plansToJson());
        return json;
    }

    // EFFECTS: returns plans in this itinerary as a JSON array
    private JSONArray plansToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Plan p : itinerary) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
