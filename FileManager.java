package com.mycompany.simpletaskscheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_NAME = "tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) {

        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Tasks saved successfully.");
        }
        catch(IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static ArrayList<Task>loadTasks() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return tasks;
            }

            Scanner fileReader = new Scanner(file);

            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                Task task = new Task(Integer.parseInt(data[0]),data[1],data[2],data[3]);
                tasks.add(task);
            }

            fileReader.close();
            System.out.println("Tasks loaded successfully.");
        }
        catch(Exception e) {

            System.out.println("Error loading tasks.");
        }

        return tasks;
    }
}