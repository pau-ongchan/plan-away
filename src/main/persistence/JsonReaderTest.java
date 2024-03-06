package persistence;

import model.Itinerary;
import model.Plan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// inspired by JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Itinerary it = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //all good!
        }
    }

    @Test
    void testReaderEmptyItinerary() {
        JsonReader reader = new JsonReader("./data/testReadEmpty.json");
        try {
            Itinerary it = reader.read();
            assertEquals("US Trip", it.getName());
            assertEquals(0, it.getNumPlans());
            assertEquals(3, it.getNumberOfDays());
        } catch (IOException e) {
            fail("Exception should not be thrown!");
        }
    }

    @Test
    void testReaderItinerary() {
        JsonReader reader = new JsonReader("./data/testRead.json");
        try {
            Itinerary it = reader.read();
            assertEquals("US Trip", it.getName());
            assertEquals(3, it.getNumberOfDays());
            LinkedList<Plan> plans = it.getAllPlans();
            assertEquals(2, plans.size());
            assertEquals("San Francisco", plans.get(0).getLocation());
            assertEquals(2, plans.get(0).getDay());
            assertEquals("Visit the Golden Gate Bridge", plans.get(0).getDescription());
            assertEquals("Los Angeles", plans.get(1).getLocation());
            assertEquals(1, plans.get(1).getDay());
        } catch (IOException e) {
            fail("Exception should not be thrown!");
        }
    }
}
