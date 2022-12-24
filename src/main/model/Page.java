package model;

import org.json.JSONArray;
import org.json.JSONObject;

// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a page in a bullet journal, where there is a date, to-do list and a journal entry
public class Page {
    private ToDoList todoList;
    private JournalEntry journal;

    // EFFECTS: constructs bullet journal page
    public Page(ToDoList todoList, JournalEntry journal) {
        this.todoList = todoList;
        this.journal = journal;
    }

    // MODIFIES: this
    // EFFECTS: adds to-do list to the bullet journal page
    public void addTodoList(ToDoList todoList) {
        this.todoList = todoList;
    }

    // MODIFIES: this
    // EFFECTS: adds journal entry to the bullet journal page
    public void addJournalEntry(JournalEntry entry) {
        journal = entry;
    }

    public ToDoList getTodoList() {
        return this.todoList;
    }


    public String getJournalEntry() {
        return journal.getEntry();
    }

    public JournalEntry getJournal() {
        return journal;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("todo list", todoListToJson());
        json.put("journal", journal.toJson());
        return json;
    }

    // EFFECTS: returns to-do list in this page as a JSON array
    private JSONArray todoListToJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(todoList.toJson());
        return jsonArray;
    }

    // EFFECTS: returns journal entry in this page as a JSON array
//    private JSONObject journalToJson() {
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("journal", journal.toJson());
//        return jsonObject;
//    }
}


