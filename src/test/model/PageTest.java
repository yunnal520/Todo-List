package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PageTest {
    Page testBulletJournalPage;
    ToDoList listA;
    JournalEntry entryA;

    Task taskA;


    @BeforeEach
    public void setup() {
        ToDoList listEmpty = new ToDoList();
        JournalEntry emptyJournal = new JournalEntry("");
        testBulletJournalPage = new Page(listEmpty, emptyJournal);
        listA = new ToDoList();
        taskA = new Task("buy food", "Tuesday");
        listA.addTask(taskA);
        entryA = new JournalEntry("Today was a busy day");
    }


    @Test
    public void testAddTodoList() {
        testBulletJournalPage.addTodoList(listA);
        JSONObject obj = testBulletJournalPage.toJson();
        System.out.println(obj);
    }

    @Test
    public void testAddJournalEntry() {
        testBulletJournalPage.addJournalEntry(entryA);
    }

}
