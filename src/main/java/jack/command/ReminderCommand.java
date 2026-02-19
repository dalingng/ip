package jack.command;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jack.storage.Storage;
import jack.task.Deadline;
import jack.task.Event;
import jack.task.Task;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * Represents a task with its due date for sorting.
 */
class TaskWithDueDate {
    private Task task;
    private LocalDateTime dueDate;
    private int index;

    TaskWithDueDate(Task task, LocalDateTime dueDate, int index) {
        this.task = task;
        this.dueDate = dueDate;
        this.index = index;
    }

    public Task getTask() {
        return task;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public int getIndex() {
        return index;
    }
}

/**
 * Command to show reminders for upcoming tasks.
 * Displays tasks that are due within a specified time period.
 */
public class ReminderCommand extends Command {

    /**
     * Executes the reminder command.
     * Shows tasks that are due within the next 24 hours, sorted by due date.
     * @param tasks The task list.
     * @param ui The UI object.
     * @param storage The storage object.
     * @return The reminder message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        LocalDateTime now = LocalDateTime.now();

        List<TaskWithDueDate> upcomingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isDone()) {
                continue;
            }
            if (!task.isDone() && hasDueDate(task)) {
                LocalDateTime dueDate = getDueDate(task);

                if (dueDate != null && dueDate.isAfter(now)) {
                    upcomingTasks.add(new TaskWithDueDate(task, dueDate, i + 1));
                }
            }
        }

        Collections.sort(upcomingTasks, Comparator.comparing(TaskWithDueDate::getDueDate));

        StringBuilder reminders = new StringBuilder();
        reminders.append("Here are your upcoming tasks:\n");

        if (upcomingTasks.isEmpty()) {
            reminders.append("No upcoming tasks.");
        } else {
            for (TaskWithDueDate taskWithDate : upcomingTasks) {
                long hoursRemaining = ChronoUnit.HOURS.between(now, taskWithDate.getDueDate());
                reminders.append(taskWithDate.getIndex() + ". " + taskWithDate.getTask().toString());
                reminders.append(" (due in " + hoursRemaining + " hours)");
                reminders.append("\n");
            }
        }

        String result = reminders.toString();
        System.out.println(result);
        ui.showLine();
        return result;
    }

    /**
     * Checks if a task has a due date.
     * @param task The task to check.
     * @return true if the task has a due date, false otherwise.
     */
    private boolean hasDueDate(Task task) {
        return task instanceof Deadline || task instanceof Event;
    }

    /**
     * Gets the due date of a task.
     * @param task The task to get the due date from.
     * @return The due date of the task, or null if the task has no due date.
     */
    private LocalDateTime getDueDate(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getDue();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getEnd();
        }
        return null;
    }
}
