package jack.command;

import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  throws Exception{
        ui.bye();
        this.isExit=true;
    }
}
