package com.mycompany.simpletaskscheduler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        TaskManager manager = new TaskManager();

        int choice;
        do {
            UserInterface.displayMenu();
            choice = Integer.parseInt(input.nextLine());

            switch(choice) {
                case 1:

                    System.out.print("Enter Task ID: ");
                    int id = Integer.parseInt(input.nextLine());
                    System.out.print("Enter Task Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Description: ");
                    String description = input.nextLine();
                    System.out.print("Enter Due Date: ");
                    String dueDate = input.nextLine();
                    Task task = new Task(id,name,description,dueDate);
                    manager.addTask(task);
                    break;

                case 2:

                    manager.viewTasks();
                    break;

                case 3:

                    System.out.print("Enter Task Name: ");
                    String searchName = input.nextLine();
                    Task foundTask = manager.searchTask(searchName);

                    if(foundTask != null) {
                        foundTask.displayTask();
                    }else {

                        System.out.println("Task not found.");
                    }
                    break;

                case 4:

                    System.out.print("Enter Task Name: ");

                    String deleteName = input.nextLine();
                    manager.deleteTask(deleteName);
                    break;

                case 5:

                    FileManager.saveTasks(manager.getTasks());
                    break;

                case 6:

                    manager.setTasks(FileManager.loadTasks());
                    break;

                case 7:

                    System.out.println("Exiting program.");
                    break;

                default:

                    System.out.println("Invalid option.");
            }

        } while(choice != 7);

        input.close();
    }
}