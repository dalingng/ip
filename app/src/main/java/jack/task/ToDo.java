package jack.task;

import jack.Excep;

/**
 * Represents a todo task, which is a type of task without any specific deadline or time.
 * Extends the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * @return A string containing the task type, status icon, and description.
     */
    @Override
    public String toString() {
        return "[" + taskName() + "]" + super.toString();
    }

    /**
     * Returns the task type name for todo tasks.
     * @return "T" for todo tasks.
     */
    @Override
    public String taskName() {
        return "T";
    }

    /**
     * Serializes the todo task to a string format for storage.
     * @return A string representation of the todo task for storage.
     */
    public String serialize() {
        return String.join(" | ", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Creates a new ToDo task from the given text.
     * @param text The description of the todo task.
     * @return A new ToDo task with the specified description.
     * @throws Excep If the text is empty.
     */
    public static ToDo taskToToDo(String text) throws Excep {
        if (text.isEmpty()) {
            throw new Excep("nothing todo i also want");
        }
        return new ToDo(text);
    }
}

