package jack.task;

import java.time.LocalDateTime;

import jack.DateTimeTool;
import jack.Excep;

/**
 * Represents a deadline task, which is a type of task with a specific deadline.
 * Extends the Task class.
 */
public class Deadline extends Task {

    protected String due;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * @param description The description of the deadline task.
     * @param due The due date of the deadline task.
     * @throws Excep If there is an error parsing the due date.
     */
    public Deadline(String description, String due) throws Excep {
        super(description);
        try {
            LocalDateTime time = DateTimeTool.parseDateTime(due);
            this.due = DateTimeTool.formatDateTime(time);
        } catch (Excep e) {
            System.out.println(e.getMessage());
            this.due = due;
        }
    }

    /**
     * Returns a string representation of the deadline task.
     * @return A string containing the task type, status icon, description, and due date.
     */
    @Override
    public String toString() {
        return "[" + taskName() + "]" + super.toString() + " (by: " + due + ")";
    }

    /**
     * Returns the task type name for deadline tasks.
     * @return "D" for deadline tasks.
     */
    @Override
    public String taskName() {
        return "D";
    }

    /**
     * Returns the due date of the deadline task.
     * @return The due date of the deadline task.
     */
    public String getDue() {
        return due;
    }

    /**
     * Creates a new Deadline task from the given task string.
     * @param task The task string containing the description and due date.
     * @return A new Deadline task with the specified description and due date.
     * @throws Excep If the task string is empty or does not contain "by".
     */
    public static Deadline taskToDeadline(String task) throws Excep {
        if (task.isEmpty()) {
            throw new Excep("no deadline i also want");
        } else if (!task.contains("by")) {
            throw new Excep("wrong format");
        }
        String[] temp = task.split(" /", 2);
        String text = temp[0];
        String tempDeadline = temp[1];
        String deadline = tempDeadline.substring(3);
        return new Deadline(text, deadline);
    }

    /**
     * Returns the task information as a string for storage.
     * @return A string containing the description and due date of the task.
     */
    public String toTask() {
        return this.getDescription() + " /by " + this.getDue();
    }
}

