package jack.command;

import jack.Excep;
import jack.storage.Storage;
import jack.task.Note;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Command to add a note to the task list.
 */
public class NoteCommand extends Command {
    private final Note note;

    /**
     * Constructs a new AddNoteCommand with the specified note.
     * @param note The note to add.
     */
    public NoteCommand(Note note) {
        this.note = note;
    }

    /**
     * Executes the add note command.
     * Adds the note to the task list.
     * @param tasks The task list.
     * @param ui The UI object.
     * @param storage The storage object.
     * @return The add note message.
     * @throws Excep If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Excep {
        ui.showLine();
        tasks.add(note);
        storage.save(tasks);
        String msg = "Got it. I've added this note:\n  " + note;
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
