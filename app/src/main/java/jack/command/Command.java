package jack.command;

import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

abstract public class Command {
    protected boolean isExit = false;

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isExit(){return  this.isExit;}

}
