package jack.ui;

import jack.task.Deadline;
import jack.task.Event;
import jack.task.Task;
import jack.task.TaskList;
import jack.task.ToDo;

/**
 * The user interface class for the Jack application.
 * Responsible for displaying messages to the user and handling user interactions.
 * AI-assisted: Cursor helped add personality to the UI responses.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    private static final String[] HAPPY_RESPONSES = {
        "Awesome! ",
        "Great! ",
        "Perfect! ",
        "Done! ",
        "Got it! "
    };

    private String getHappyResponse() {
        return HAPPY_RESPONSES[(int) (Math.random() * HAPPY_RESPONSES.length)];
    }

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
     * @return The formatted todo message.
     */
    public String todo(TaskList list, ToDo todo) {
        System.out.println(LINE);
        String msg = getHappyResponse() + "I've added this task:\n  " + todo
                + "\nYou're doing great! Keep going! ";
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
     * @return The formatted deadline message.
     */
    public String deadline(TaskList list, Deadline deadline) {
        System.out.println(LINE);
        String msg = getHappyResponse() + "New deadline added:\n  " + deadline
                + "\nDon't worry, I'll remind you before it's due! ";
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
     * @return The formatted event message.
     */
    public String event(TaskList list, Event event) {
        System.out.println(LINE);
        String msg = getHappyResponse() + "Event added! Looking forward to it:\n  " + event;
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
     * @return The formatted delete message.
     */
    public String delete(TaskList list, Task t) {
        System.out.println(LINE);
        String msg = "Alright! I've removed this task for you:\n  " + t
                + "\nSometimes letting go is the best choice! ";
        System.out.println(msg);
        String numMsg = "Now you have " + list.size() + " tasks in the list.";
        System.out.println(numMsg);
        System.out.println(LINE);
        msg += "\n" + numMsg;
        return msg;
    }

    /**
     * Displays a message when a task is undone successfully.
     * @param list The current task list.
     * @return The formatted undo message.
     */
    public String undo(TaskList list) {
        System.out.println(LINE);
        String msg = "No problem! I've undone that for you "
                + "\nIt's okay to change your mind - we all do! ";
        System.out.println(msg);
        String numMsg = "Now you have " + list.size() + " tasks in the list.";
        System.out.println(numMsg);
        System.out.println(LINE);
        msg += "\n" + numMsg;
        return msg;
    }

    /**
     * Displays a friendly welcome message when the application starts.
     * @return The welcome message.
     */
    public String welcome() {
        System.out.println(LINE);
        String msg = "Hi there! I'm Jack "
                + "\nI'm here to help you manage your tasks! "
                + "\nWhat can I do for you today? ";
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays a goodbye message when the application exits.
     * @return The goodbye message.
     */
    public String displayBye() {
        String msg = "Bye bye! "
                + "\nIt was nice chatting with you! "
                + "\nCome back soon, okay? ";
        System.out.println(LINE);
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays all tasks in the current task list.
     * @param list The task list to display.
     * @return The formatted task list.
     */
    public String list(TaskList list) {
        System.out.println(LINE);
        String msg = "Here's your task list! You can do this! \n";
        System.out.println(msg);

        StringBuilder resultMsg = new StringBuilder(msg);
        final int[] index = {1};

        list.stream()
            .forEach(task -> {
                String taskMsg = index[0] + ". " + task.toString();
                System.out.println(taskMsg);
                resultMsg.append(taskMsg).append("\n");
                index[0]++;
            });

        System.out.println(LINE);
        return resultMsg.toString();
    }

    /**
     * Displays a message when a task is marked as done.
     * @return The mark message.
     */
    public String mark() {
        System.out.println(LINE);
        String msg = "Wow! You completed a task! "
                + "\nYou're amazing! Here's what you finished:\n";
        System.out.println(msg);
        return msg;
    }

    /**
     * Displays the task that was marked as done and a line separator.
     * @param t The task that was marked as done.
     * @return The mark success message.
     */
    public String markSuccess(Task t) {
        String msg = "  " + t + "\nCelebrate this small win! ";
        System.out.println(msg);
        System.out.println(LINE);
        return msg;
    }

    /**
     * Displays a message when a task is marked as not done.
     * @return The unmark message.
     */
    public String unmark(Task t) {
        System.out.println(LINE);
        String msg = "No worries! I've unmarked this task for you:\n"
                + "  " + t.toString()
                + "\nTake your time, no rush! \n";
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
     * @return The formatted search results.
     */
    public String find(TaskList list, String keyword) {

        System.out.println(LINE);

        StringBuilder resultMsg = new StringBuilder("");
        final int[] count = {1};

        list.stream()
            .filter(task -> task.getDescription().contains(keyword))
            .forEach(task -> {
                String taskMsg = count[0] + ". " + task.toString();
                System.out.println(taskMsg);
                resultMsg.append(taskMsg).append("\n");
                count[0]++;
            });

        if (count[0] == 1) {
            resultMsg.append("No tasks found with the keyword: " + keyword);
        }



        System.out.println(LINE);
        return resultMsg.toString();
    }

    /**
     * Returns a friendly error message based on the error type.
     * @param errorMsg The original error message.
     * @return A friendly error message with suggestions.
     */
    public String getFriendlyError(String errorMsg) {
        String lower = errorMsg.toLowerCase();
        if (lower.contains("empty") || lower.contains("cannot be empty")) {
            return "Could you please try again with some text? ";
        } else if (lower.contains("wrong command") || lower.contains("unsupported")) {
            return "Sorry, I don't understand that command yet! \n"
                + "Try: todo, deadline, event, list, mark, unmark, delete, find, undo";
        } else if (lower.contains("invalid task") || lower.contains("task number")) {
            return "Oops! That's not a valid task number.\n"
                + errorMsg + "\n"
                + "You can type 'list' to see all your tasks. ";
        } else if (lower.contains("deadline") && lower.contains("format")) {
            return "Let me help you with the deadline format!\n"
                + "Try: deadline <description> /by <date>\n"
                + "Example: deadline submit report /by 25/12/2024 1800";
        } else if (lower.contains("event") && lower.contains("format")) {
            return "Let me help you with the event format!\n"
                + "Try: event <name> /from <start> /to <end>\n"
                + "Example: event party /from 25/12/2024 1800 /to 26/12/2024 0000";
        }
        return "Hmm, something went wrong there.\n"
            + errorMsg + "\nDon't worry, let's try again! ";
    }
}
