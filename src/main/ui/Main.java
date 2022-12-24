package ui;

import java.io.FileNotFoundException;

// This class references code from the JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class Main {
    public static void main(String[] args) {
        try {
            new BulletJournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}