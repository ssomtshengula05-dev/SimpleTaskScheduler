# Simple Task Scheduler

A Java desktop application that helps users organise tasks, manage deadlines and track completion. Users interact with the application through Swing `JOptionPane` dialogs and can save their task list to a local text file.

## Group 28

| Member | Student number |
| --- | --- |
| Somtshengula Siphumelele | 222778555 |
| Mthembu Owethu | 231194412 |

## Purpose

Students and workers often manage several tasks with different deadlines. This application keeps task details in one place and helps users identify unfinished work that is already overdue.

## Features

- Add a task with an ID, name, description and deadline.
- View all tasks and their completion status.
- Search for a task by its numeric ID.
- Update a task's name, description and deadline.
- Mark a task as complete.
- Delete a task by ID.
- Save tasks to `tasks.txt` and load them in another session.
- Check for incomplete tasks whose deadlines have passed.

The overdue check runs when the user selects it from the menu. Automatic background notifications and sorting by deadline are not implemented.

## Technologies

- Java
- Swing and `JOptionPane`
- Maven project structure
- `ArrayList<Task>` for task storage in memory
- `LocalDateTime` for deadlines
- `FileWriter` and `Scanner` for file handling
- Apache NetBeans as the project development environment

No external application libraries or database server are required. The dialog interface requires a graphical desktop environment.

## Setup and running

### Requirements

- A Java Development Kit. The supplied source was successfully compiled with JDK 17 during presentation preparation.
- Apache NetBeans with Java support for the IDE instructions below.
- Maven if building from the command line.

### Check the project configuration first

The supplied `pom.xml` declares Java release `27` and names `com.mycompany.simpletaskscheduler.SimpleTaskScheduler` as its execution entry point. The actual entry point in the source is `Main`.

For a JDK 17 setup, update the two existing properties in `pom.xml` to:

```xml
<maven.compiler.release>17</maven.compiler.release>
<exec.mainClass>com.mycompany.simpletaskscheduler.Main</exec.mainClass>
```

These are setup corrections to apply to the supplied project. This README does not change the project files itself.

### Run in NetBeans

1. Download and extract the project, or clone its repository.
2. In NetBeans, choose **File > Open Project**.
3. Select the project folder containing `pom.xml`.
4. Confirm the project uses your installed JDK and the matching compiler release.
5. Build the project.
6. Open `Main.java` in the `com.mycompany.simpletaskscheduler` package and run that file. If using **Run Project**, set the main class to `com.mycompany.simpletaskscheduler.Main`.
7. Enter a menu number in the input dialog.

### Run from a terminal

After correcting the Maven properties, open a terminal in the folder containing `pom.xml`:

```sh
mvn clean compile
java -cp target/classes com.mycompany.simpletaskscheduler.Main
```

The application opens dialog windows. Use the terminal to check file-handling error messages.

## Using the application

| Option | Action |
| --- | --- |
| 1 | Add Task |
| 2 | View Tasks |
| 3 | Search Task |
| 4 | Update Task |
| 5 | Mark Complete |
| 6 | Delete Task |
| 7 | Save Tasks |
| 8 | Load Tasks |
| 9 | Check Overdue Tasks |
| 10 | Exit |

Use numeric task IDs and keep them unique. For deadlines, enter the following format, including the letter `T` between the date and time:

```text
2026-11-20T14:30
```

This means 20 November 2026 at 14:30, using a 24-hour clock. Enter the same format when updating a deadline. Dates use the computer's local date and time without a time-zone field.

New tasks start with `completed` set to `false`. The overdue check reports a task only when it is incomplete and its deadline is earlier than the current time.

**Saving is manual.** Select option 7 before exiting if you want to keep your changes. Select option 8 after starting another session to restore saved tasks. Loading replaces the list currently in memory. Saving overwrites the existing file.

## File storage

The application reads and writes `tasks.txt` in its current working directory. When running in an IDE, this location depends on the run configuration. Keep the working directory consistent between sessions.

Each task occupies one line with five semicolon-separated fields:

```text
taskID;taskName;description;dueDate;completed
```

Example record:

```text
101;Project report;Prepare final draft;2026-11-20T14:30;false
```

Loading parses each record into a `Task` object and restores the completion status. If the file does not exist, loading returns an empty list. Avoid semicolons in task names and descriptions because the current format does not escape them.

## System design

| Class | Responsibility |
| --- | --- |
| `Main` | Runs the menu loop and connects user choices to application operations. |
| `UserInterface` | Displays the menu and task information using dialogs. |
| `Task` | Stores private task fields and provides methods to access or change task state. |
| `TaskManager` | Maintains the task list and performs add, search, update, delete and display operations. |
| `FileManager` | Saves and reconstructs task objects using a local text file. |
| `SchedulerService` | Scans tasks and reports incomplete overdue items. |

All six source files are in `src/main/java/com/mycompany/simpletaskscheduler/`. The project also contains `pom.xml`, NetBeans action settings in `nbactions.xml`, and a test-source directory. Maven generates compiled output in `target/`.

## Data structures and algorithms

Tasks are stored in an `ArrayList<Task>`, which grows as tasks are added. Searching by ID uses a linear scan: the best case takes one comparison, while the worst case takes O(n) comparisons for n tasks. Updating and deleting first locate a task using that search.

The overdue check also scans the list, giving O(n) time complexity. It includes a task only when both conditions hold:

```java
!task.isCompleted() && task.getDueDate().isBefore(LocalDateTime.now())
```

The code demonstrates classes and objects, encapsulation, collections, conditional logic, loops, date/time handling and file operations. The current application does not start a scheduler thread.

## Testing

The project report contains manual test screenshots. During presentation preparation, the newer source compiled successfully and a separate temporary harness passed 12 core-logic checks covering:

- Empty-list behaviour and loading when a file is missing.
- Adding tasks and searching for existing or missing IDs.
- Updating task details and deleting existing or missing IDs.
- Marking tasks complete and filtering overdue items.
- Saving and loading while preserving task details and completion status.

These checks cover core logic, not automated interaction with the graphical dialogs. The temporary harness is not included in the supplied project, which has no implemented automated tests in its test-source directory.

To verify the user workflow, add a future task and an incomplete past task, check overdue results, complete the past task, and repeat the check. Save the list, restart the application, load the file and confirm the saved details. Finally, delete a task and confirm that searching for its ID returns no result.

## Known limitations

- Invalid numeric input, invalid date input or cancelled input can cause unhandled exceptions.
- Duplicate task IDs are accepted, and ID searches return the first match.
- Some operations provide limited feedback when an ID does not exist.
- File errors print to the console, but the menu can still display a success message. Verify the file or restored list after saving or loading.
- Damaged file records are not handled individually, so loading may return only the records read before an error.
- There is no automatic saving, deadline sorting or background reminder service. The reminder methods currently print a message only.

## Planned improvements

Future work includes stronger input validation, duplicate-ID prevention, reliable error feedback, safer file parsing, deadline sorting and automatic reminders.

## Repository contents

Keep the Java source, `pom.xml`, relevant project settings, this README and supporting documentation in the repository. Generated `target/` output does not need to be committed. Use sample tasks rather than personal task records when sharing example data
