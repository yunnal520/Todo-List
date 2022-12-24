package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Bullet journal application
// This BulletJournalApp references code from the TellerApp
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class BulletJournalApp {
    private static String JSON_STORE = "./data/bulletjournal.json";
    private BulletJournal bulletJournal; // Being might cause weird behaviours!!
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Task task;
    private JournalEntry journal;

    // EFFECT: Runs the bullet journal application
    public BulletJournalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        bulletJournal = new BulletJournal();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBulletJournal();
    }

    // MODIFIES: this
    // EFFECT: processes user input
    private void runBulletJournal() {
//        boolean keepGoing = true;
//        String command;
//
//        initialize();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//
//            } else {
////                processCommand(command);
//            }
//        }
//        System.out.println("Have a nice day! :)");
    }

// Don't need for GUI
//    // MODIFIES: this
//    // EFFECT: processes the user command
//    private void processCommand(String command) {
//
//        if (command.equals("t")) {
//            editToDoList();
//        } else if (command.equals("j")) {
//            editJournalEntry();
//        } else {
//            return;
//        }
//   }

    // MODIFIES: this
    // EFFECT: initializes the bullet journal
    private void initialize() {
        bulletJournal = new BulletJournal();
        input = new Scanner(System.in);
    }

//    // EFFECT: Displays the menu option of the bullet journal to the user
//    private void displayMenu() {
//        System.out.println("\n Select from:");
//        System.out.println("\t t -> TodoList");
//        System.out.println("\t j -> Journal Entry");
//        System.out.println("\t q -> quit");
//    }


    // EFFECTS: Displays the menu option of the bullet journal to the user
    private void displayMenu() {
        String selection = "";

        while (!(selection.equals("c") || selection.equals("n"))) {
            System.out.println("\n Select option:");
            System.out.println("\t p -> Previous Page");
            System.out.println("\t n -> New Page");
            System.out.println("\t v -> View bullet journal");
            System.out.println("\t s -> Save bullet journal");
            System.out.println("\t l -> Load bullet journal");
            System.out.println("\t q -> Quit");
            selection = input.nextLine();
            selection = selection.toLowerCase();
            selectPageOutcome(selection);
        }
    }


    // EFFECT: selects page to go to (previous/current/new)
    private void selectPageOutcome(String selection) {

        if (selection.equals("p")) {
            editPreviousPage();
        } else if (selection.equals("n")) {
            addPageToBulletJournal();
        } else if (selection.equals("v")) {
            printBulletJournal();
        } else if (selection.equals("s")) {
            saveBulletJournal();
        } else if (selection.equals("l")) {
            loadBulletJournal();
        } else if (selection.equals("q")) {
            System.out.println("Have a nice day! :)");
            System.exit(0);
        } else {
            return;
        }
    }

    // MODIFIES: this
    // EFFECT: add page to bullet journal
    public void addPageToBulletJournal() {
        ToDoList listEmpty = new ToDoList();
        JournalEntry emptyJournalEntry = new JournalEntry("");
        Page page = new Page(listEmpty, emptyJournalEntry);
        bulletJournal.addBulletJournalPage(page);
//        editPage(); // Don't need for GUI
    }

    // MODIFIES: this
    // EFFECT: edit the selected previous page
    public void editPreviousPage() {
        Integer pageNum;
        if (bulletJournal.length() <= 1) {
            System.out.println("There are no previous pages");
        } else {
            System.out.println("Select a page to edit:");
            pageNum = input.nextInt();
            input.nextLine();
// Don't need for GUI
//            editPreviousPageOptions(pageNum);
        }
    }

    // MODIFIES: this
    // EFFECTS: Prompts user to choose option to edit and selects functionality to edit; edits to-do list and journal
    private void editPreviousPageOptions(int pageNum) {
        String journalEntry;
        String description;
        String deadline;
        String selection;

        System.out.println("\n Choose option:");
        System.out.println("\t t -> Todo List");
        System.out.println("\t j -> Journal Entry");

        selection = input.nextLine();
        selection = selection.toLowerCase();

        if (selection.equals("t")) {
            System.out.println("Write a task description:");
            description = input.nextLine();
            System.out.println("Write a task deadline:");
            deadline = input.nextLine();
            Task taskTodo = new Task(description, deadline);
            bulletJournal.setBulletJournalTodoListEntry(taskTodo, pageNum);
        } else if (selection.equals("j")) {
            System.out.println("Write a journal entry:");
            journalEntry = input.nextLine();
            bulletJournal.setBulletJournalJournalEntry(journalEntry, pageNum);

        }
    }

    // MODIFIES: this
    // EFFECTS: helper method for editing to-do list on the previous page (for GUI)
    public void editPreviousTodoList(String description, String deadline, int pageNum) {
        Task taskTodo = new Task(description, deadline);
        bulletJournal.setBulletJournalTodoListEntry(taskTodo, pageNum);
    }

    // MODIFIES: this
    // EFFECTS: helper method for editing journal entry on the previous page (for GUI)
    public void editPreviousJournalEntry(String journal, int pageNum) {
        bulletJournal.setBulletJournalJournalEntry(journal, pageNum);
    }





    // EFFECT: prompts the user to choose to-do list or journal entry to edit
    public void editPage() {
        String selection = "";

        System.out.println("\n Choose option:");
        System.out.println("\t t -> Todo List");
        System.out.println("\t j -> Journal Entry");
        System.out.println("\t r -> Return to display menu");

        selection = input.nextLine();
        selection = selection.toLowerCase();
//        selectEditOutcome(selection);

    }

// Don't need for GUI
//    // EFFECT: selects bullet journal functionality to add
//    public void selectEditOutcome(String selection) {
//
//        if (selection.equals("t")) {
//           x
//        } else if (selection.equals("j")) {
//            editJournalEntry();
//        } else if (selection.equals("r")) {
//            displayMenu();
//        } else {
//            editPage();
//        }
//    }

    // MODIFIES: this
    // EFFECTS: enter a to-do list to bullet journal page
    public void editToDoList(String description, String deadline) {

        Task todo = new Task(description, deadline);
        bulletJournal.setBulletJournalTodoListEntry(todo, bulletJournal.length());

// Don't need for GUI
//        String description;
//        String deadline;
//        System.out.println("Write a task description:");
//        description = input.nextLsine();
//        System.out.println("Write a task deadline:");
//        deadline = input.nextLine();

//        viewCompleteAndIncompleteTasks(); // Don't need for GUI
//        editPage();
    }

    public Task getTask() {
        return task;
    }

    public JournalEntry getJournalEntry() {
        return journal;
    }

    public BulletJournal getBulletJournalClass() {
        return bulletJournal;
    }


//    // EFFECTS: shows the amount of complete and incomplete tasks on the to-do list
//    private void viewCompleteAndIncompleteTasks() {
//        System.out.println("Amount of Complete Tasks: ");
//        System.out.println(bulletJournal.viewNumOfCompleteTasks(bulletJournal.length()));
//
//        System.out.println("Amount of Incomplete Tasks: ");
//        System.out.println(bulletJournal.viewNumOfIncompleteTasks(bulletJournal.length()));
//    }


    // MODIFIES: this
    // EFFECTS: enter a journal entry to bullet journal page
    public void editJournalEntry(String journalEntry) {

// Don't need for GUI
//        String journalEntry;
//        System.out.println("Write a journal entry:");
//        journalEntry = input.nextLine();
        bulletJournal.setBulletJournalJournalEntry(journalEntry, bulletJournal.length());
//        editPage();
    }

    // EFFECTS: print bullet journal to view
    public void printBulletJournal() {
        System.out.println(bulletJournal.viewBulletJournal());

    }
//
//    // EFFECTS: returns the string for bullet journal
//    public String getBulletJournalString() {
//        return bulletJournal.viewBulletJournal();
//    }


    // EFFECTS: saves project to file
    public void saveBulletJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(bulletJournal);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads bullet journal from file
    public void loadBulletJournal() {
        try {
            bulletJournal = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
