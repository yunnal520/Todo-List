package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This JsonReaderTest class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {
    Page pageA;
    Page pageB;

    JournalEntry entryA;
    JournalEntry entryB;

    Task taskA;
    Task taskB;

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BulletJournal by = reader.read();
            fail("IOException expected");
        } catch (IOException e){

        }
    }

    @Test
    void testReaderEmptyBulletJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBulletJournal.json");
        try {
            BulletJournal bj = reader.read();
            assertEquals(0, bj.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBulletJournal() {
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

        JsonReader reader = new JsonReader("./data/testReaderGeneralBulletJournal.json");
        try {
            BulletJournal bj = reader.read();
            assertEquals(2, bj.length());
            checkPage(listA, entryA, pageA);
            checkPage(listB, entryB, pageB);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
