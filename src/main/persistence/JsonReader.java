package persistence;

import model.Itinerary;
import model.Plan;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads an itinerary from JSON data stored in file
// inspired by JsonSerializationDemo
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Itinerary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseItinerary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses itinerary from JSON object and returns it
    private Itinerary parseItinerary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int days = jsonObject.getInt("days");
        Itinerary it = new Itinerary(name, days);
        addPlans(it, jsonObject);
        return it;
    }

    // MODIFIES: it
    // EFFECTS: parses plans from JSON object and adds them to itinerary
    private void addPlans(Itinerary it, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("plans");
        for (Object json : jsonArray) {
            JSONObject nextPlan = (JSONObject) json;
            addPlan(it, nextPlan);
        }
    }

    // MODIFIES: it
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPlan(Itinerary it, JSONObject jsonObject) {
        int day = jsonObject.getInt("day");
        String location = jsonObject.getString("location");
        String description = jsonObject.getString("description");
        Plan plan = new Plan(day, location, description);
        it.addPlan(plan);
    }


}
