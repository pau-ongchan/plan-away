package ui;

import model.Itinerary;
import model.Plan;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

// Represents the itinerary application
// code inspired by the TellerApp and JsonSerializationDemo
public class ItineraryApp {
    private static final String JSON_STORE = "./data/itinerary.json";
    private Itinerary it;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the Itinerary Application
    public ItineraryApp() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runItinerary();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runItinerary() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("x")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\n I hope you enjoy your trip!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("+")) {
            doAddPlan();
        } else if (command.equals("-")) {
            doDeletePlan();
        } else if (command.equals("v")) {
            doViewPlan();
        } else if (command.equals("r")) {
            doReset();
        } else if (command.equals("s")) {
            doSave();
        } else if (command.equals("l")) {
            doLoad();
        } else {
            System.out.println("Selection not valid. Please choose again.");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes itinerary
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Name: ");
        String name = input.next();
        System.out.println("Trip Length: ");
        int numOfDays = input.nextInt();
        it = new Itinerary(name,numOfDays);
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nItinerary Name: " + it.getName());
        System.out.println("\nItinerary Length: " + it.getNumberOfDays());
        System.out.println("\nSelect from:");
        System.out.println("\t [+] -> Add Plan");
        System.out.println("\t [-] -> Remove Plan");
        System.out.println("\t [V] -> View Plan");
        System.out.println("\t [R] -> Reset");
        System.out.println("\t [X] -> Exit");
    }

    //MODIFIES: this
    //EFFECTS: adds the plan to the itinerary
    private void doAddPlan() {
        System.out.println("Location: ");
        String loc = input.next();
        System.out.println("Day: ");
        int day = input.nextInt();
        System.out.println("Description: ");
        String desc = input.next();
        Plan plan = new Plan(0, null, null);

        if (day > it.getNumberOfDays()) {
            System.out.println("Please choose a day between 1 and " + it.getNumberOfDays());
        } else {
            plan.setLocation(loc);
            plan.setDay(day);
            plan.setDescription(desc);
            it.addPlan(plan);
            System.out.println("Added to your itinerary!");
            System.out.println(" \n Day: " + day + "\n Location: " + loc + "\n Description: " + desc);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the plan from the itinerary
    private void doDeletePlan() {
        System.out.println("Delete which location? ");
        String loc = input.next();

        if (it.removePlan(loc)) {
            System.out.println(loc + " has been deleted.");
        } else {
            System.out.println("Could not find location.");
        }
    }

    //EFFECTS: Display the plans according to their days
    private void doViewPlan() {
        System.out.println("Here is your itinerary: \n");
        int day = 0;
        LinkedList<Plan> listOfPlans = it.viewItinerary(); // Retrieve the latest itinerary information
        for (Plan p : listOfPlans) {
            if (day != p.getDay()) {
                System.out.println("\n Day " + p.getDay());
                System.out.println(p.getLocation() + "  -  " + p.getDescription());
                day = p.getDay();
            } else {
                System.out.println(p.getLocation() + "  -  " + p.getDescription());
            }
        }
        System.out.println("----- End -----");
    }

    //MODIFIES: this
    //EFFECTS: Removes all the plans in the current itinerary
    private void doReset() {
        it.resetItinerary();
        System.out.println("Itinerary has been reset.");
    }

    // EFFECTS: saves the itinerary to file
    private void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(it);
            jsonWriter.close();
            System.out.println("Saved " + it.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads itinerary from file
    private void doLoad() {
        try {
            it = jsonReader.read();
            System.out.println("Loaded " + it.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
