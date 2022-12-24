package persistence;

import model.JournalEntry;
import model.Page;
import model.Task;
import model.ToDoList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This JsonTest class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
//    protected void checkTask(String description, String deadline, Task task) {
//        assertEquals(description, task.getDescription());
//        assertEquals(deadline, task.getDeadline());
//    }
    protected void checkPage(ToDoList todoList, JournalEntry journal, Page page) {
        assertEquals(todoList, page.getTodoList());
        assertEquals(journal, page.getJournal());
    }
}
