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
        try {
            this.start = DateTimeTool.parseDateTime(start);
            this.end = DateTimeTool.parseDateTime(end);
        } catch (Exception e) {
            throw new Excep("wrong event format");
        }
    }

    @Override
    public String toString() {
        return "[" + taskName() + "]" + super.toString() + " (from: "
                + DateTimeTool.formatDateTime(start) + " to: "
                + DateTimeTool.formatDateTime(end) + ")";
    }

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

        int fromIndex = task.indexOf("/from");
        int toIndex = task.indexOf("/to");
        String text;
        String fromStr;
        String toStr;

        if (fromIndex < toIndex) {
            text = task.substring(0, fromIndex).trim();
            fromStr = task.substring(fromIndex + 5, toIndex).trim();
            toStr = task.substring(toIndex + 3).trim();
        } else {
            text = task.substring(0, toIndex).trim();
            toStr = task.substring(toIndex + 3, fromIndex).trim();
            fromStr = task.substring(fromIndex + 5).trim();
        }

        if (text.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
            throw new Excep("wrong event format");
        }

        LocalDateTime fromTime = DateTimeTool.parseDateTime(fromStr);
        LocalDateTime toTime = DateTimeTool.parseDateTime(toStr);

        if (toTime.isBefore(fromTime)) {
            throw new Excep("/from cannot be later than /to time");
        }
        try {
            DateTimeTool.parseDateTime(fromStr);
            DateTimeTool.parseDateTime(toStr);
        } catch (Exception e) {
            throw new Excep("wrong event format");
        }
        return new Event(text, fromStr, toStr);
    }

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
