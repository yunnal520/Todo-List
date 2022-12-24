package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a bullet journal, contains a list of pages
public class BulletJournal {

    private ArrayList<Page> bulletJournal;

    // EFFECT: Constructs an empty bullet journal
    public BulletJournal() {
        bulletJournal = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds a bullet journal page bullet journal
    public void addBulletJournalPage(Page page) {
        bulletJournal.add(page);
        EventLog.getInstance().logEvent(new Event("Added page: " + (bulletJournal.size())));
    }

    // EFFECT: returns the total number of bullet journal pages
    public int length() {
        return bulletJournal.size();
    }

    // EFFECT: returns true if the bullet journal is empty
    public boolean bulletJournalIsEmpty() {
        return bulletJournal.isEmpty();
    }

    // MODIFIES: this
    // EFFECT: Edit the journal entry at Page (pageNum - 1)
    public void setBulletJournalJournalEntry(String entry, Integer pageNum) {
        Page toEdit = bulletJournal.get(pageNum - 1);
        toEdit.getJournal().setEntry(entry);
        EventLog.getInstance().logEvent(new Event("Added journal entry: " + entry.toString()));
    }

    public ArrayList<Page> getBulletJournalField() {
        return bulletJournal;
    }

    // MODIFIES: this
    // EFFECT: Edit the task at Page (pageNum - 1)
    public void setBulletJournalTodoListEntry(Task task, Integer pageNum) {
        Page toEdit = bulletJournal.get(pageNum - 1);
        toEdit.getTodoList().addTask(task);
        ToDoList t = new ToDoList();
        EventLog.getInstance().logEvent(new Event("Added to to-do list: " + t.getTodoListName()));
    }

    // EFFECT: view number of complete tasks on to-do list
    public int viewNumOfCompleteTasks(Integer pageNum) {
        Page getCompleteTasks = bulletJournal.get(pageNum - 1);
        return getCompleteTasks.getTodoList().numberOfCompleteTasks();
    }

    // EFFECT: view number of incomplete tasks on to-do list
    public int viewNumOfIncompleteTasks(Integer pageNum) {
        Page getIncompleteTasks = bulletJournal.get(pageNum - 1);
        return getIncompleteTasks.getTodoList().numberOfIncompleteTasks();
    }


    // EFFECT: views the bullet journal
    public String viewBulletJournal() {
        StringBuilder bulletJournalString = new StringBuilder();
        for (Page p : bulletJournal) {
            bulletJournalString.append("Todo List: ").append(p.getTodoList().viewTodoList())
                    .append("Journal: ").append(p.getJournalEntry()).append("\n");
//Taken out for GUI
//                    .append("Amount of Complete Tasks: ").append(p.getTodoList().numberOfCompleteTasks()).append("\n")
//                    .append("Amount of Incomplete Tasks: ")
//                    .append(p.getTodoList().numberOfIncompleteTasks()).append("\n");
        }
        return bulletJournalString.toString();
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Bullet Journal", bulletJournalToJson());
        return json;
    }

    // EFFECTS: returns things in this bullet journal as a JSON array
    private JSONArray bulletJournalToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Page p : bulletJournal) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
