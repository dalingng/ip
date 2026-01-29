package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.Task;
import jack.task.TaskList;
import jack.ui.Ui;

public class MarkCommand extends Command{
    private int idx;
    public MarkCommand(int idx){
        this.idx = idx - 1;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if(idx < 1 || idx >= tasks.size()){
            throw new Excep("no such task number");
        }
        Task t = tasks.get(idx);
        ui.mark();
        t.mark();
        storage.save(tasks);
        ui.markSuccess(t);
    }
}
