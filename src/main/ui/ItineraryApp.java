package ui;

import model.Itinerary;
import model.Plan;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Scanner;

public class ItineraryApp {
    // code inspired by the TellerApp
    private Itinerary it;
    private Scanner input;

    //EFFECTS: runs the Itinerary Application
    public ItineraryApp() {
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
        } else {
            System.out.println("Selection not valid. Please choose again.");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes itinerary
    private void init() {
        it = new Itinerary(0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Trip Length: ");
        int numOfDays = input.nextInt();
        it.setDay(numOfDays);

    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
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
            System.out.println("Please choose a day between 1 and " +  it.getNumberOfDays());
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

    //EFFECTS:

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

//    private void doViewPlan() {
//        System.out.println("Here is your itinerary: \n");
//        int day = 0;
//        LinkedList<Plan> listOfPlans = it.viewItinerary();
//        for (Plan p : listOfPlans) {
//            if (day != p.getDay()) {
//                System.out.println("\n Day " + p.getDay());
//                System.out.println(p.getLocation() + "  -  " + p.getDescription());
//                day = p.getDay();
//            } else {
//                System.out.println(p.getLocation() + "  -  " + p.getDescription());
//            }
//        }
//        System.out.println("----- End -----");
//    }

    private void doReset() {
        it.resetItinerary();
        System.out.println("Itinerary has been reset.");
    }



}
