package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItineraryTest {

    private Itinerary testItinerary;
    private Itinerary filledItinerary;
    private Itinerary arrangedItinerary;
    private Plan p1;
    private Plan p2;
    private Plan p3;
    private Plan p4;


    @BeforeEach
    void runBefore() {
        testItinerary = new Itinerary("Korea Trip",3);
        filledItinerary = new Itinerary("BC Trip",4);
        arrangedItinerary = new Itinerary("US Trip",4);
        p1 = new Plan(1, "Stanley", "Picnic with friends");
        p2 = new Plan(3, "Whistler", "Skiing with friends");
        p3 = new Plan(1, "Pacific Center", "Eat Sushi");
        p4 = new Plan(2, "Whistler", "Hiking alone");
        filledItinerary.addPlan(p1);
        filledItinerary.addPlan(p2);
        filledItinerary.addPlan(p3);
        filledItinerary.addPlan(p4);
        arrangedItinerary.addPlan(p1);
        arrangedItinerary.addPlan(p3);
        arrangedItinerary.addPlan(p4);
        arrangedItinerary.addPlan(p2);


    }

    @Test
    void testConstructor() {
        assertEquals(3, testItinerary.getNumberOfDays());
        assertEquals(0, testItinerary.getNumPlans());
        assertEquals("Korea Trip", testItinerary.getName());
    }

    @Test
    void testAddPlan() {
        testItinerary.addPlan(p1);
        assertEquals(p1, testItinerary.getPlan(0));
        assertEquals(1, testItinerary.getNumPlans());
        testItinerary.addPlan(p2);
        assertEquals(p2, testItinerary.getPlan(1));
        assertEquals(2, testItinerary.getNumPlans());
    }

    @Test
    void testRemovePlan() {
        assertTrue(filledItinerary.removePlan("Whistler"));
        assertEquals(3, filledItinerary.getNumPlans());
        assertEquals(p1, filledItinerary.getPlan(0));
        assertEquals(p3, filledItinerary.getPlan(1));
        assertEquals(p4, filledItinerary.getPlan(2));
        assertFalse(filledItinerary.removePlan("Mexico"));
        assertEquals(3, filledItinerary.getNumPlans());
    }

    @Test
    void testViewItinerary() {
        assertEquals(p1, filledItinerary.viewItinerary().get(0));
        assertEquals(p3, filledItinerary.viewItinerary().get(1));
        assertEquals(p4, filledItinerary.viewItinerary().get(2));
        assertEquals(p2, filledItinerary.viewItinerary().get(3));
    }

    @Test
    void testResetItinerary() {
        filledItinerary.resetItinerary();
        assertEquals(0, filledItinerary.getNumPlans());
    }

    @Test
    void testSetDay() {
        filledItinerary.setDay(2);
        assertEquals(2, filledItinerary.getNumberOfDays());
    }
}