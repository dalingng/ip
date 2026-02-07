package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.Task;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to delete a task from the Jack application.
 * Extends the Command class.
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructs a new DeleteCommand with the specified task index.
     * @param idx The index of the task to delete (1-based).
     */
    public DeleteCommand(int idx) {
        this.idx = idx - 1;
    }

    /**
     * Executes the delete command, which removes the specified task from the task list.
     * @param tasks The task list containing the task to delete.
     * @param ui The UI to display the result.
     * @param storage The storage to save the updated task list.
     * @throws Exception If the task index is invalid or an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (idx < 0 || idx >= tasks.size()) {
            throw new Excep("no such task number");
        }
        Task t = tasks.get(idx);
        tasks.remove(idx);
        storage.save(tasks);
        ui.delete(tasks, t);
    }
}
