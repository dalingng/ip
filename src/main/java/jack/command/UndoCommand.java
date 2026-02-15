package jack.command;

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
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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

    /**
     * Returns false as this command does not exit the application.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
