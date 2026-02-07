package jack.command;

import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to find tasks by keyword in the Jack application.
 * Extends the Command class.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which searches for tasks containing the keyword.
     * @param tasks The task list to search in.
     * @param ui The UI to display the search results.
     * @param storage The storage (not used in this command).
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.find(tasks, keyword);
    }
}
