package com.mycompany.simpletaskscheduler;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;
    public TaskManager() {

        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    public void viewTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        
        for (Task task : tasks) {
            task.displayTask();
        }
    }

    public Task searchTask(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                return task;
            }
        }

        return null;
    }

    public void deleteTask(String taskName) {
        Task task = searchTask(taskName);
        if (task != null) {tasks.remove(task);
            System.out.println("Task deleted successfully.");
        }
        else {

            System.out.println("Task not found.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(
            ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}