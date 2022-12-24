package model;

import org.json.JSONArray;
import org.json.JSONObject;

// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a journal entry; contains an entry
public class JournalEntry {

    private String entry;

    //EFFECT: Constructs journal entry
    public JournalEntry(String entry) {
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entry", entry);
        return json;
    }
}