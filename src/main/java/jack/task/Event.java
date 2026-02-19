package jack.task;

import java.time.LocalDateTime;

import jack.DateTimeTool;
import jack.Excep;

/**
 * Represents an event task, which is a type of task with a specific start and end time.
 * Extends the Task class.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     * @param description The description of the event task.
     * @param start The start time of the event task.
     * @param end The end time of the event task.
     * @throws Excep If there is an error parsing the start or end time.
     */
    public Event(String description, String start, String end) throws Excep {
        super(description);
        this.start = DateTimeTool.parseDateTime(start);
        this.end = DateTimeTool.parseDateTime(end);
    }

    /**
     * Returns a string representation of the event task.
     * @return A string containing the task type, status icon, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[" + taskName() + "]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the task type name for event tasks.
     * @return "E" for event tasks.
     */
    @Override
    public String taskName() {
        return "E";
    }

    /**
     * Creates a new Event task from the given task string.
     * @param task The task string containing the description, start time, and end time.
     * @return A new Event task with the specified description, start time, and end time.
     * @throws Excep If the task string is empty or does not contain "/from" or "/to".
     */
    public static Event taskToEvent(String task) throws Excep {
        if (task.isEmpty()) {
            throw new Excep("event description cannot be empty");
        } else if (!task.contains("/from") || !task.contains("/to")) {
            throw new Excep("wrong event format");
        }
        String[] temp = task.split(" /", 3);
        String text = temp[0];
        String tempStart = temp[1];
        String start = tempStart.substring(5);
        String tempEnd = temp[2];
        String end = tempEnd.substring(3);
        return new Event(text, start, end);
    }

    /**
     * Returns the task information as a string for storage.
     * @return A string containing the description, start time, and end time of the task.
     */
    @Override
    public String toTask() {
        return this.getDescription() + " /from "
                + DateTimeTool.formatDateTime(start) + " /to "
                + DateTimeTool.formatDateTime(end);
    }

    /**
     * Returns the start time of the event task.
     * @return The start time of the event task.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Returns the end time of the event task.
     * @return The end time of the event task.
     */
    public LocalDateTime getEnd() {
        return end;
    }
}
