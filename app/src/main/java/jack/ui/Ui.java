package jack.ui;

import jack.task.Deadline;
import jack.task.Event;
import jack.task.Task;
import jack.task.TaskList;
import jack.task.ToDo;

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
    public String todo(TaskList list, ToDo todo) {
        System.out.println(LINE);
        String msg = "Got it. I've added this task:\n  " + todo;
        System.out.println(msg);
        System.out.println(LINE);
        String numMsg = "Now you have " + list.size() + " tasks in the list.";
        System.out.println(numMsg);
        msg += "\n" + numMsg;
        return msg;
    }

    /**
     * Displays a message when a deadline task is added successfully.
     * @param list The current task list.
     * @param deadline The deadline task that was added.
     */
    public String deadline(TaskList list, Deadline deadline) {
        System.out.println(LINE);
        String msg = "Deadline task added: " + deadline + "\n " + deadline;
        System.out.println(msg);
        System.out.println(LINE);
        String numMsg = "Now you have " + list.size() + " tasks in the list.";
        System.out.println(numMsg);
        msg += "\n" + numMsg;
        return msg;
    }

    /**
     * Displays a message when an event task is added successfully.
     * @param list The current task list.
     * @param event The event task that was added.
     */
    public String event(TaskList list, Event event) {
        System.out.println(LINE);
        String msg = "Got it. I've added this task:\n  " + event;
        System.out.println(msg);
        String numMsg = "Now you have " + list.size() + " tasks in the list.";
        System.out.println(numMsg);
        msg += "\n" + numMsg;
        return msg;
    }

    /**
     * Displays a message when a task is deleted successfully.
     * @param list The current task list.
     * @param t The task that was deleted.
     */
    public String delete(TaskList list, Task t) {
        System.out.println(LINE);
        String msg = "Noted. I've removed this task:\n  " + t;
        System.out.println(msg);
        String numMsg = "Now you have " + list.size() + " tasks in the list.";
        System.out.println(numMsg);
        System.out.println(LINE);
        msg += "\n" + numMsg;
        return msg;
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public String welcome() {
        System.out.println(LINE);
        String msg = "Hello I'm Jack\nWhat can I do for you?";
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays a goodbye message when the application exits.
     */
    public String displayBye() {
        String msg = "Bye. Hope to see you again soon!";
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays all tasks in the current task list.
     * @param list The task list to display.
     */
    public String list(TaskList list) {
        System.out.println(LINE);
        String msg = "Here are the tasks in your list:\n";
        System.out.println(msg);
        for (int j = 1; j <= list.size(); j++) {
            String taskMsg = j + "." + list.get(j - 1).toString();
            msg += "\n" + taskMsg;
            System.out.println(taskMsg);
        }
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays a message when a task is marked as done.
     */
    public String mark() {
        System.out.println(LINE);
        String msg = "Nice! I've marked this task as done:\n";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays the task that was marked as done and a line separator.
     * @param t The task that was marked as done.
     */
    public String markSuccess(Task t) {
        String msg = "  " + t;
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays a message when a task is marked as not done.
     */
    public String unmark() {
        System.out.println(LINE);
        String msg = "OK, I've marked this task as not done yet:\n";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays the task that was marked as not done and a line separator.
     * @param t The task that was marked as not done.
     */
    public String unmarkSuccess(Task t) {
        String msg = "  " + t;
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
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
    public String find(TaskList list, String keyword) {
        System.out.println(LINE);
        String msg = "Here are the matching tasks in your list:\n";
        System.out.println(msg);
        int count = 1;
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                String taskMsg = count + "." + task.toString();
                System.out.println(taskMsg);
                msg += taskMsg + "\n";
                count++;
            }
        }
        System.out.println(LINE);
        return msg;
    }
}
