package persistence;


import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// This JsonReader class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads BulletJournal from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public BulletJournal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBulletJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bullet journal from JSON object and returns it
    private BulletJournal parseBulletJournal(JSONObject jsonObject) {
        BulletJournal bj = new BulletJournal();
        addPages(bj, jsonObject);
        return bj;
    }

    // MODIFIES: bj
    // EFFECTS: parses pages from JSON OBJECT and adds them to Bullet Journal
    private void addPages(BulletJournal bj, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Bullet Journal");
        for (Object json : jsonArray) {
            JSONObject nextPage = (JSONObject) json;
            addPage(bj, nextPage);
        }
    }

    // MODIFIES: page
    // EFFECTS: parses page from JSON object and adds them to bullet journal
    private void addPage(BulletJournal bj, JSONObject jsonObject) {
        JSONObject journal = jsonObject.getJSONObject("journal");
        String entry = journal.getString("entry");
        JournalEntry journalEntry = new JournalEntry(entry);

        ToDoList todoList = new ToDoList();
        addTasks(todoList, jsonObject);

        Page page = new Page(todoList, journalEntry);
        bj.addBulletJournalPage(page);
    }

    // MODIFIES: todoList
    // EFFECTS: parses tasks from JSON object and adds them to a to-do list
    public void addTasks(ToDoList todoList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("todo list");
        for (Object json : jsonArray) {
            JSONArray jsonArrayOfTasks = ((JSONObject) json).getJSONArray("tasks");
            for (Object task : jsonArrayOfTasks) {
                JSONObject nextTask = (JSONObject) task;
                addTask(todoList, nextTask);
            }
        }
    }

    // MODIFIES: to-do
    // EFFECTS: parses task from JSON object and adds it to a to-do list
    private void addTask(ToDoList todo, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        String deadline = jsonObject.getString("deadline");
        Task task = new Task(description, deadline);
        todo.addTask(task);
    }
}
