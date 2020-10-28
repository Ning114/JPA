package persistence;

import model.Problem;
import model.ProblemSet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

//This entire class's implementation is heavily based off of the JsonReader class from the JsonSerializationDemo.
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads ProblemSet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProblemSet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProblemSet(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses ProblemSet from JSON object and returns it
    private ProblemSet parseProblemSet(JSONObject jsonObject) {
        String displayType = jsonObject.getString("displayType");
        ProblemSet ps = new ProblemSet(displayType);
        addProblemSet(ps, jsonObject);
        return ps;
    }

    // MODIFIES: ProblemSet
    // EFFECTS: parses Problem from JSON object and adds them to ProblemSet
    private void addProblemSet(ProblemSet ps, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("problemSet");
        for (Object json : jsonArray) {
            JSONObject nextProblem = (JSONObject) json;
            addProblem(ps, nextProblem);
        }
    }

    // MODIFIES: ProblemSet
    // EFFECTS: parses Problem from JSON object and adds it to ProblemSet
    private void addProblem(ProblemSet ps, JSONObject jsonObject) {

        String japaneseProblem = jsonObject.getString("japaneseProblem");
        String englishProblem = jsonObject.getString("englishProblem");
        //not going to bother retrieving score, it gets set to 0 anyways

        Problem p = new Problem(japaneseProblem, englishProblem);
        ps.problemSet.add(p);
    }

}
