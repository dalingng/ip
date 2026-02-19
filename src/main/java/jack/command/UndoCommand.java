package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Command to undo the last command.
 * Reverts the task list to the previous state.
 */
public class UndoCommand extends Command {

    /**
     * Executes the undo command.
     * Restores the task list to the previous state.
     * @param tasks The current task list.
     * @param ui The UI object.
     * @param storage The storage object.
     * @return The undo message.
     * @throws Excep If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        if (tasks.isEmpty()) {
            ui.showLine();
            String msg = "No previous state to undo to.";
            System.out.println(msg);
            ui.showLine();
            return msg;
        }

        tasks.remove(tasks.size() - 1);
        return ui.undo(tasks);
    }
}
