package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import ui.BulletJournalApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This JsonWriterTest class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {
    Page pageA;
    Page pageB;

    JournalEntry entryA;
    JournalEntry entryB;

    Task taskA;
    Task taskB;

    @Test
    void testWriterInvalidFile() {
        try {
            BulletJournal bj = new BulletJournal();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterEmptyBulletJourna() {
        try {
            BulletJournal bj = new BulletJournal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBulletJournal.json");
            writer.open();
            writer.write(bj);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBulletJournal.json");
            bj = reader.read();
            assertEquals(0, bj.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBulletJournal() {
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

        try {
            BulletJournal bj = new BulletJournal();
            bj.addBulletJournalPage(pageA);
            bj.addBulletJournalPage(pageB);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBulletJournal.json");
            writer.open();
            writer.write(bj);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBulletJournal.json");
            bj = reader.read();
            assertEquals(2, bj.length());
            checkPage(listA, entryA, pageA);
            checkPage(listB, entryB, pageB);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
