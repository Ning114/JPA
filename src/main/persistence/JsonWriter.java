package persistence;

import model.ProblemSet;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//CITATION:
//This class is heavily based off of the JsonWriter class from the JsonSerializationDemo.
//The toJson() method added to ProblemSet is also based off of the one in the JsonSerializationDemo.
public class JsonWriter {

    //TABS might need to be changed later on. I suspect it has to do with the index of the .json file being accessed
    //eg. the file on the top of the list in the data package has the index 0, and it moves downwards from there.
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ProblemSet to file
    public void write(ProblemSet ps) {
        JSONObject json = ps.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }


}
