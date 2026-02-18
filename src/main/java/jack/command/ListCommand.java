package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to list all tasks in the Jack application.
 * Extends the Command class.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, which displays all tasks in the task list.
     * @param tasks The task list to display.
     * @param ui The UI to display the tasks.
     * @param storage The storage (not used in this command).
     * @throws Excep If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        return ui.list(tasks);
    }
}
