package jack.command;

import jack.storage.Storage;
import jack.task.Event;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a command to add an event task in the Jack application.
 * Extends the Command class.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Constructs a new EventCommand with the specified event task.
     * @param event The event task to add.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the event command, which adds a new event task to the task list.
     * @param tasks The task list to add the event task to.
     * @param ui The UI to display the result.
     * @param storage The storage to save the updated task list.
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.add(event);
        storage.save(tasks);
        return ui.event(tasks, event);
    }
}
