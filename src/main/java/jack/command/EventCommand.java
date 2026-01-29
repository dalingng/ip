package jack.command;

import jack.storage.Storage;
import jack.task.Event;
import jack.task.TaskList;
import jack.ui.Ui;

public class EventCommand extends Command{
    private Event event;

    public EventCommand(Event event){
        this.event=event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.add(event);
        storage.save(tasks);
        ui.event(tasks, event);
    }
}
