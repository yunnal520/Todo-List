package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BulletJournalTest {
    BulletJournal testBulletJournal;
    ArrayList<Page> bulletJournal;
    Page pageA;
    Page pageB;
    Page pageC;

    JournalEntry entryA;
    JournalEntry entryB;
    JournalEntry entryC;

    Task taskA;
    Task taskB;
    Task taskC;


    @BeforeEach
    public void setup() {
        testBulletJournal = new BulletJournal();
        ToDoList listA = new ToDoList();
        taskA = new Task("Math HW", "November 10");
        listA.addTask(taskA);
        entryA = new JournalEntry("Today was a nice day.");
        pageA = new Page(listA, entryA);

        ToDoList listB = new ToDoList();
        taskB = new Task("English Reading", "November 23");
        listB.addTask(taskB);
        entryB = new JournalEntry("I finished all my homework today!");
        pageB = new Page(listB, entryB);

        ToDoList listC = new ToDoList();
        taskC = new Task("Get groceries for hotpot", "today before 5pm");
        listC.addTask(taskC);
        entryC = new JournalEntry("I had hotpot with my family today.");
        pageC = new Page(listC, entryC);
    }

    @Test
    public void testAddBulletJournalPageToEmptyBulletJournal() {
        assertTrue(testBulletJournal.bulletJournalIsEmpty());
        testBulletJournal.addBulletJournalPage(pageA);
        assertEquals(1, testBulletJournal.length());
    }

    @Test
    public void testAddBulletJournalPageToBulletJournalWithPage() {
        testBulletJournal.addBulletJournalPage(pageA);
        assertEquals(1, testBulletJournal.length());
        testBulletJournal.addBulletJournalPage(pageC);
        assertEquals(2, testBulletJournal.length());
    }


    @Test
    public void testViewBulletJournal() {
        testBulletJournal.addBulletJournalPage(pageB);
        assertEquals("Todo List: - English Reading (November 23)\n" +
                        "Journal: I finished all my homework today!\n",
//                        + "Amount of Complete Tasks: 0\n" + "Amount of Incomplete Tasks: 1\n",
                testBulletJournal.viewBulletJournal());
    }

    @Test
    public void testSetBulletJournalJournalEntry() {
        testBulletJournal.addBulletJournalPage(pageB);
        testBulletJournal.setBulletJournalJournalEntry("Today was a bad day.", 1);
        assertEquals("Todo List: - English Reading (November 23)\n" +
                        "Journal: Today was a bad day.\n",
//                        + "Amount of Complete Tasks: 0\n" + "Amount of Incomplete Tasks: 1\n"
                testBulletJournal.viewBulletJournal());

    }

    @Test
    public void testSetBulletJournalToDoList() {
        testBulletJournal.addBulletJournalPage(pageA);
        Task taskNew = new Task("English HW", "November 12");
        testBulletJournal.setBulletJournalTodoListEntry(taskNew, 1);
    }

    @Test
    public void testGetNumOfCompleteAndIncompleteTasks() {
        testBulletJournal.addBulletJournalPage(pageA);
        assertEquals(1, testBulletJournal.viewNumOfIncompleteTasks(1));
        assertEquals(0, testBulletJournal.viewNumOfCompleteTasks(1));
    }

    @Test
    public void testGetBulletJournalField() {
        bulletJournal = new ArrayList<>();
        assertEquals(bulletJournal, testBulletJournal.getBulletJournalField());
    }
}

