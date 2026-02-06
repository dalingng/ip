package jack.ui;

import jack.task.*;

/**
 * The user interface class for the Jack application.
 * Responsible for displaying messages to the user and handling user interactions.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    
    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a message when a todo task is added successfully.
     * @param list The current task list.
     * @param todo The todo task that was added.
     */
    public void todo(TaskList list, ToDo todo) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println(LINE);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays a message when a deadline task is added successfully.
     * @param list The current task list.
     * @param deadline The deadline task that was added.
     */
    public void deadline(TaskList list, Deadline deadline) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println(LINE);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays a message when an event task is added successfully.
     * @param list The current task list.
     * @param event The event task that was added.
     */
    public void event(TaskList list, Event event) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println(LINE);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
    
    /**
     * Displays a message when a task is deleted successfully.
     * @param list The current task list.
     * @param t The task that was deleted.
     */
    public void delete(TaskList list, Task t) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void welcome() {
        System.out.println(LINE);
        System.out.println("Hello I'm Jack");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message when the application exits.
     */
    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays all tasks in the current task list.
     * @param list The task list to display.
     */
    public void list(TaskList list) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int j = 1; j <= list.size(); j++) {
            System.out.println(j + "." + list.get(j - 1).toString());
        }
        System.out.println(LINE);
    }
    
    /**
     * Displays a message when a task is marked as done.
     */
    public void mark() {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
    }
    
    /**
     * Displays the task that was marked as done and a line separator.
     * @param t The task that was marked as done.
     */
    public void markSuccess(Task t) {
        System.out.println("  " + t);
        System.out.println(LINE);
    }

    /**
     * Displays a message when a task is marked as not done.
     */
    public void unmark() {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
    }
    
    /**
     * Displays the task that was marked as not done and a line separator.
     * @param t The task that was marked as not done.
     */
    public void unmarkSuccess(Task t) {
        System.out.println("  " + t);
        System.out.println(LINE);
    }

    /**
     * Displays an error message to the user.
     * @param massage The error message to display.
     */
    public void showError(String massage) {
        System.out.println(LINE);
        System.out.println(massage);
        System.out.println(LINE);
    }

    /**
     * Displays tasks that match the given keyword.
     * @param list The task list to search in.
     * @param keyword The keyword to search for.
     */
    public void find(TaskList list, String keyword) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(count + "." + task.toString());
                count++;
            }
        }
        System.out.println(LINE);
    }
}
