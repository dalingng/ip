package jack.task;

import java.time.LocalDateTime;

import jack.DateTimeTool;
import jack.Excep;

/**
 * Represents a deadline task, which is a type of task with a specific deadline.
 * Extends the Task class.
 */
public class Deadline extends Task {

    protected LocalDateTime due;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * @param description The description of the deadline task.
     * @param due The due date of the deadline task.
     * @throws Excep If there is an error parsing the due date.
     */
    public Deadline(String description, String due) throws Excep {
        super(description);
        try {
            this.due = DateTimeTool.parseDateTime(due);
        } catch (Exception e) {
            throw new Excep("wrong deadline format");
        }
    }

    @Override
    public String toString() {
        return "[" + taskName() + "]" + super.toString()
                + " (by: " + DateTimeTool.formatDateTime(due) + ")";
    }

    @Override
    public String taskName() {
        return "D";
    }

    /**
     * Returns the due date of the deadline task.
     * @return The due date of the deadline task.
     */
    public LocalDateTime getDue() {
        return due;
    }

    /**
     * Creates a new Deadline task from the given task string.
     * @param task The task string containing the description and due date.
     * @return A new Deadline task with the specified description and due date.
     * @throws Excep If the task string is empty or does not contain "/by".
     */
    public static Deadline taskToDeadline(String task) throws Excep {
        if (task.isEmpty()) {
            throw new Excep("deadline description cannot be empty");
        } else if (!task.contains("/by")) {
            throw new Excep("wrong deadline format");
        }
        String[] temp = task.split("/by");
        if (temp.length != 2) {
            throw new Excep("wrong deadline format");
        }
        String text = temp[0].trim();
        if (text.isEmpty()) {
            throw new Excep("wrong deadline format");
        }
        String deadline = temp[1].trim();
        return new Deadline(text, deadline);
    }

    @Override
    public String toTask() {
        return this.getDescription() + " /by " + DateTimeTool.formatDateTime(due);
    }
}
