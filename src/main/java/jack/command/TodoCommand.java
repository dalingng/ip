package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.task.ToDo;
import jack.ui.Ui;

/**
 * Represents a command to add a todo task in the Jack application.
 * Extends the Command class.
 */
public class TodoCommand extends Command {
    private String text;

    /**
     * Constructs a new TodoCommand with the specified todo task description.
     * @param text The description of the todo task to add.
     */
    public TodoCommand(String text) {
        this.text = text;
    }

    /**
     * Executes the todo command, which adds a new todo task to the task list.
     * @param tasks The task list to add the todo task to.
     * @param ui The UI to display the result.
     * @param storage The storage to save the updated task list.
     * @throws Excep If an error occurs during execution, such as if the todo description is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        ToDo todo = ToDo.taskToToDo(text);
        tasks.add(todo);
        storage.save(tasks);
        return ui.todo(tasks, todo);
    }
}
