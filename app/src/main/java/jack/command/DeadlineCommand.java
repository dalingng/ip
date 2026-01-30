package jack.command;

import jack.storage.Storage;
import jack.task.Deadline;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to add a deadline task in the Jack application.
 * Extends the Command class.
 */
public class DeadlineCommand extends Command{
    private Deadline deadline;

    /**
     * Constructs a new DeadlineCommand with the specified deadline task.
     * @param deadline The deadline task to add.
     */
    public DeadlineCommand(Deadline deadline){
        this.deadline=deadline;
    }

    /**
     * Executes the deadline command, which adds a new deadline task to the task list.
     * @param tasks The task list to add the deadline task to.
     * @param ui The UI to display the result.
     * @param storage The storage to save the updated task list.
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.add(deadline);
        storage.save(tasks);
        ui.deadline(tasks, deadline);
    }
}
