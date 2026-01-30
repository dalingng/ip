package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.Task;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to mark a task as not done in the Jack application.
 * Extends the Command class.
 */
public class UnmarkCommand extends Command{
    private int idx;
    
    /**
     * Constructs a new UnmarkCommand with the specified task index.
     * @param idx The index of the task to mark as not done (1-based).
     */
    public UnmarkCommand(int idx){
        this.idx = idx - 1;
    }
    
    /**
     * Executes the unmark command, which marks the specified task as not done.
     * @param tasks The task list containing the task to unmark.
     * @param ui The UI to display the result.
     * @param storage The storage to save the updated task list.
     * @throws Exception If the task index is invalid or an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if(idx < 1 || idx >= tasks.size()){
            throw new Excep("no such task number");
        }
        Task t = tasks.get(idx);
        ui.unmark();
        t.unmark();
        storage.save(tasks);
        ui.unmarkSuccess(t);
    }
}
