
package persistence;

import org.json.JSONObject;
//This class was modeled after JsonSerializationDemo

public interface Writeable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
