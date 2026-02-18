package jack.task;

import java.time.LocalDateTime;

import jack.DateTimeTool;
import jack.Excep;

/**
 * Represents an event task, which is a type of task with a specific start and end time.
 * Extends the Task class.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     * @param description The description of the event task.
     * @param start The start time of the event task.
     * @param end The end time of the event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        try {
            LocalDateTime startTime = DateTimeTool.parseDateTime(start);
            this.start = DateTimeTool.formatDateTime(startTime);

            LocalDateTime endTime = DateTimeTool.parseDateTime(end);
            this.end = DateTimeTool.formatDateTime(endTime);
        } catch (Excep e) {
            System.out.println(e.getMessage());
            this.start = start;
            this.end = end;
        }
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
     * @throws Excep If the task string is empty or does not contain "from" or "to".
     */
    public static Event taskToEvent(String task) throws Excep {
        if (task.isEmpty()) {
            throw new Excep("no event i also want");
        } else if (!task.contains("from") || (!task.contains("to"))) {
            throw new Excep("wrong format");
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
    public String toTask() {
        return this.getDescription() + " /from " + this.getStart() + " /to " + this.getEnd();
    }

    /**
     * Returns the start time of the event task.
     * @return The start time of the event task.
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns the end time of the event task.
     * @return The end time of the event task.
     */
    public String getEnd() {
        return end;
    }
}

