package jack.command;

import jack.storage.Storage;
import jack.task.Deadline;
import jack.task.TaskList;
import jack.ui.Ui;

public class DeadlineCommand extends Command{
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline){
        this.deadline=deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.add(deadline);
        storage.save(tasks);
        ui.deadline(tasks, deadline);
    }
}
