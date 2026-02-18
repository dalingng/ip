package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to exit the Jack application.
 * Extends the Command class.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command, which displays a goodbye message and sets the exit flag.
     * @param tasks The task list (not used in this command).
     * @param ui The UI to display the goodbye message.
     * @param storage The storage (not used in this command).
     * @throws Excep If an error occurs during execution.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        this.isExit = true;
        return ui.displayBye();
    }
}
