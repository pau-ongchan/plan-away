package ui;

import model.Itinerary;
import model.Plan;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;


// the itinerary application commands
public class ItineraryController {
    private static final String JSON_STORE = "./data/itinerary.json";
    private Itinerary itinerary;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private DashboardPanel dashboardPanel;

    //EFFECTS: creates an instance of jsonWriter and jsonReader
    public ItineraryController() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: sets the dashboard panel used for displaying itineraries
    public void setDashboardPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
    }

    //MODIFIES: this
    //EFFECTS: creates an instance of an itinerary with given name and number of days
    public void createNewItinerary(String name, int numOfDays) {
        itinerary = new Itinerary(name, numOfDays);
    }

    // MODIFIES: this
    // EFFECTS: loads itinerary from file, throws IOException if the file cannot be read
    public void loadItinerary() {
        try {
            itinerary = jsonReader.read();
            System.out.println("Loaded " + itinerary.getName() + " from " + JSON_STORE);
            viewItinerary();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds plan to the itinerary, updates the dashboard
    //REQUIRES: location != null, day !=0, description !=null,
    public void addPlan(String location, int day, String description) {
        Plan plan = new Plan(day, location, description);
        itinerary.addPlan(plan);
        System.out.println(location + " has been added!");
        viewItinerary();
    }

    //REQUIRES: location != null
    //MODIFIES: this
    //EFFECTS: removes the plan from the itinerary, updates dashboard if successful
    //         if not print message in the console
    public void deletePlan(String location) {
        if (itinerary.removePlan(location)) {
            System.out.println(location + " has been deleted");
            viewItinerary();
        } else {
            System.out.println("Could not find location!");
        }
    }

    //MODIFIES: this
    //EFFECTS: resets the itinerary to empty, updates the dashboard
    public void resetItinerary() {
        itinerary.resetItinerary();
        System.out.println("Itinerary has been reset");
        viewItinerary();
    }

    // EFFECTS: saves the itinerary to file
    public void saveItinerary() {
        try {
            jsonWriter.open();
            jsonWriter.write(itinerary);
            jsonWriter.close();
            System.out.println("Saved " + itinerary.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: Display the plans according to their days if initialized
    //         If not, print message in console
    public void viewItinerary() {
        if (dashboardPanel != null) {
            String itineraryInfo = formatItineraryDetails();
            dashboardPanel.updateItineraryDisplay(itineraryInfo);
        } else {
            System.out.println("Dashboard panel not initialized.");
        }
    }


    // EFFECTS: Returns a string that formats the details of the current itinerary
    private String formatItineraryDetails() {
        StringBuilder builder = new StringBuilder();
        LinkedList <Plan> listOfPlans = itinerary.viewItinerary();
        for (Plan plan : listOfPlans) {
            builder.append("\n Day ").append(plan.getDay())
                    .append("\n Location: ").append(plan.getLocation())
                    .append("\n Description: ").append(plan.getDescription())
                    .append("\n");
        }
        return builder.toString();
    }

}
