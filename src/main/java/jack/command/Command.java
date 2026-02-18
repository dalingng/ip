package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * The abstract base class for all commands in the Jack application.
 * Defines the basic interface for commands, including execution and exit status.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the command with the specified task list, UI, and storage.
     * @param tasks The task list to operate on.
     * @param ui The UI to interact with the user.
     * @param storage The storage to save/load tasks.
     * @throws Excep If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Excep;

    /**
     * Returns whether the command is an exit command.
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
