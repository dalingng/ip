package jack.command;

import jack.Excep;
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
     * @throws Excep If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        tasks.add(event);
        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new Excep("Error saving task list:" + e.getMessage());
        }
        return ui.event(tasks, event);
    }
}
