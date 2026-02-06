package jack.task;

/**
 * The base class for all types of tasks.
 * Represents a general task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     * @return A string containing the status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task type name.
     * @return "N" for the base Task class.
     */
    public String taskName() {
        return "N";
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task information as a string for storage.
     * @return The description of the task.
     */
    public String toTask() {
        return this.getDescription();
    }

    /**
     * Deletes the task.
     * This method is intended to be overridden by subclasses if needed.
     */
    public void delete() {

    }
}