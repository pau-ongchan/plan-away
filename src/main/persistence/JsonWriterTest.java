package persistence;

import model.Itinerary;
import model.Plan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

//
// inspired by JsonSerializationDemo
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Itinerary it = new Itinerary("JP Trip", 4);
            JsonWriter writer = new JsonWriter("./data/my illegal\0fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // all good
        }
    }

    @Test
    void testWriterEmptyItinerary() {
        try {
            Itinerary it = new Itinerary("JP Trip", 4);
            JsonWriter writer = new JsonWriter("./data/testWriteEmpty.json");
            writer.open();
            writer.write(it);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmpty.json");
            it = reader.read();
            assertEquals("JP Trip", it.getName());
            assertEquals(4, it.getNumberOfDays());
            assertEquals(0, it.getNumPlans());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterItinerary() {
        try {
            Itinerary it = new Itinerary("JP Trip", 4);
            it.addPlan(new Plan(3, "Osaka", "Universal Studios"));
            it.addPlan(new Plan(1, "Tokyo", "Eat Wagyu"));
            JsonWriter writer = new JsonWriter("./data/testWrite.json");
            writer.open();
            writer.write(it);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWrite.json");
            it = reader.read();
            assertEquals("JP Trip", it.getName());
            assertEquals(4, it.getNumberOfDays());
            assertEquals(2, it.getNumPlans());
            LinkedList<Plan> plans = it.getAllPlans();
            assertEquals("Osaka", plans.get(0).getLocation());
            assertEquals(3, plans.get(0).getDay());
            assertEquals("Universal Studios", plans.get(0).getDescription());
            assertEquals("Tokyo", plans.get(1).getLocation());
            assertEquals(1, plans.get(1).getDay());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
