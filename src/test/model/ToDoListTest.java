package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    ToDoList testList;
    Task taskA;
    Task taskB;
    Task taskC;
    Task taskD;
    Task taskE;

    @BeforeEach
    public void setup() {
        testList = new ToDoList();
        taskA = new Task("Math HW", "Tuesday");
        taskB = new Task("CPSC210 Lab", "Wednesday");
        taskC = new Task("Statistics Notes", "Monday");
        taskD = new Task("Get groceries for mom", "Tuesday");
        taskE = new Task("Buy gift for brother", "Friday");
    }

    @Test
    public void testAddTaskEmpty() {
        assertTrue(testList.todoListIsEmpty());
        testList.addTask(taskA);
        assertEquals(1, testList.totalTasks());
    }

    @Test
    public void testAddTaskNotEmpty() {
        testList.addTask(taskB);
        testList.addTask(taskD);
        assertEquals(2, testList.totalTasks());
        testList.addTask(taskE);
        assertEquals(3, testList.totalTasks());
    }

    @Test
    public void testDeleteTaskWithNoRemainingTasks() {
        testList.addTask(taskB);
        testList.addTask(taskD);
        assertEquals(2, testList.totalTasks());
        testList.deleteTask(taskB);
        testList.deleteTask(taskD);
        assertEquals(0, testList.totalTasks());
    }

    @Test
    public void testDeleteTaskWithRemainingTasks() {
        testList.addTask(taskA);
        testList.addTask(taskB);
        testList.addTask(taskC);
        assertEquals(3, testList.totalTasks());
        testList.deleteTask(taskB);
        assertEquals(2, testList.totalTasks());
    }

    @Test
    public void testClearToDoList() {
        testList.addTask(taskC);
        testList.addTask(taskB);
        assertEquals(2, testList.totalTasks());
        testList.clearTodoList();
        assertEquals(0, testList.totalTasks());
    }

    @Test
    public void testNumberOfCompleteTasks() {
        testList.addTask(taskA);
        assertEquals(1, testList.totalTasks());
        testList.markAsComplete(taskA);
        assertEquals(1, testList.numberOfCompleteTasks());
        assertEquals(0, testList.numberOfIncompleteTasks());
    }

    @Test
    public void testNumberOfIncompleteTasks() {
        testList.addTask(taskA);
        testList.addTask(taskB);
        testList.addTask(taskC);
        assertEquals(3, testList.totalTasks());
        assertEquals(3, testList.numberOfIncompleteTasks());
    }

    @Test
    public void testNumberOfIncompleteTasksEmptyList() {
       assertTrue(testList.todoListIsEmpty());
       assertEquals(0, testList.totalTasks());
    }

    @Test
    public void testNumberOfSomeCompletedTask() {
        testList.addTask(taskA);
        testList.addTask(taskB);
        testList.addTask(taskC);
        assertEquals(3, testList.totalTasks());
        testList.markAsComplete(taskB);
        assertEquals(1, testList.numberOfCompleteTasks());
        assertEquals(2, testList.numberOfIncompleteTasks());
    }

    @Test
    public void testViewTodoList() {
        testList.addTask(taskA);
        testList.addTask(taskB);
        assertEquals("- Math HW (Tuesday)\n" + "- CPSC210 Lab (Wednesday)\n", testList.viewTodoList());
    }

}


















