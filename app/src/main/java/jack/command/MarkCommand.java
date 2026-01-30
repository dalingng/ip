package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.Task;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to mark a task as done in the Jack application.
 * Extends the Command class.
 */
public class MarkCommand extends Command{
    private int idx;
    
    /**
     * Constructs a new MarkCommand with the specified task index.
     * @param idx The index of the task to mark as done (1-based).
     */
    public MarkCommand(int idx){
        this.idx = idx - 1;
    }
    
    /**
     * Executes the mark command, which marks the specified task as done.
     * @param tasks The task list containing the task to mark.
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
        ui.mark();
        t.mark();
        storage.save(tasks);
        ui.markSuccess(t);
    }
}
