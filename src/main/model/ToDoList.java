package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a to-do list having a list of items that are waiting to be completed or are already completed
public class ToDoList {

    private ArrayList<Task> todoList;

    // EFFECT: Constructs an empty to-do list
    public ToDoList() {
        todoList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds a to-do to the to-do list
    public void addTask(Task todo) {
        todoList.add(todo);
        EventLog.getInstance().logEvent(new Event("Added task: " + todo.getDescription()
                + "(" + todo.getDeadline() + ")"));
    }

    // REQUIRES: Number of tasks on list is > 0
    // MODIFIES: this
    // EFFECT: deletes a task from the list
    public void deleteTask(Task todo) {
        todoList.remove(todo);
    }

    // REQUIRES: Number of tasks on list is >= 1
    // MODIFIES: this
    // EFFECT: marks a task as completed
    public void markAsComplete(Task todo) {
        todo.setDone(true);
    }

    public String getTodoListName() {
        return "To-do List for Today";
    }


    // EFFECT: counts the number of complete tasks on to-do list; if the task is complete, add 1
    public int numberOfCompleteTasks() {
        int completeNumber = 0;
        for (Task t : todoList) {
            if (t.getDone()) {
                completeNumber = completeNumber + 1;
                return completeNumber;
            }
        }
        return 0;
    }

    // EFFECT: counts the number of incomplete tasks on to-do list; if the task is incomplete, add 1
    public int numberOfIncompleteTasks() {
        int incompleteNumber = 0;
        for (Task t : todoList) {
            if (!t.getDone()) {
                incompleteNumber = todoList.size() - numberOfCompleteTasks();
                return incompleteNumber;
            }
        }
        return incompleteNumber;
    }

    // EFFECT: returns the total number of tasks currently on to-do list
    public int totalTasks() {
        return todoList.size();
    }

    // EFFECT: returns true if the to-do list is empty
    public boolean todoListIsEmpty() {
        return todoList.isEmpty();
    }

    // REQUIRES: Number of tasks on list is >= 1
    // MODIFIES: this
    // EFFECT: clears the to-do list
    public void clearTodoList() {
        todoList.clear();
    }


    // EFFECT: returns the to-do list as a string
    public String viewTodoList() {
        StringBuilder listString = new StringBuilder();
        for (Task t : todoList) {
            listString.append("- ").append(t.getDescription()).append(" (").append(t.getDeadline()).append(")\n");
        }
        return listString.toString();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns tasks in this to-do list as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Task t : todoList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}