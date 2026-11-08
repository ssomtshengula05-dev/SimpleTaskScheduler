package com.mycompany.simpletaskscheduler;

public class Task {
    private int taskID;
    private String taskName;
    private String description;
    private String dueDate;
    private boolean completed;

    public Task(int taskID,String taskName,String description,String dueDate) {

        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public String toFileString() {

        return taskID + ","+ taskName + ","+ description + ","+ dueDate + ","+ completed;
    }

    public void displayTask() {
        System.out.println("\nTask ID: "+ taskID);
        System.out.println("Task Name: "+ taskName);
        System.out.println("Description: "+ description);
        System.out.println("Due Date: "+ dueDate);
        System.out.println("Completed: "+ completed);
    }
}