package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.Note;
import jack.task.Task;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Command to list all notes in the task list.
 */
public class ListNotesCommand extends Command {
    /**
     * Executes the list notes command.
     * Displays all notes in the task list.
     * @param tasks The task list.
     * @param ui The UI object.
     * @param storage The storage object.
     * @return The list notes message.
     * @throws Excep If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        ui.showLine();
        StringBuilder result = new StringBuilder();
        result.append("Here are your notes:\n");

        int noteCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Note) {
                noteCount++;
                result.append(noteCount + ". " + task.toString() + "\n");
            }
        }

        if (noteCount == 0) {
            result.append("No notes found.");
        }

        String msg = result.toString();
        System.out.println(msg);
        ui.showLine();
        return msg;
    }

    /**
     * Returns false as this command does not exit the application.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
