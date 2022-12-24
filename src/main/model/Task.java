package model;

import org.json.JSONArray;
import org.json.JSONObject;

// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a task that needs to be done; contains a description, completion status, and deadline
public class Task {
    private String description;
    private Boolean done;
    private String deadline;

    //EFFECTS: Constructs task
    public Task(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
        done = false;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public boolean getDone() {
        return done;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setDeadline(String deadline) {
//        this.deadline = deadline;
//    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("deadline", deadline);
        return json;
    }
}
