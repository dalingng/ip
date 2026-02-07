package jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jack.task.Deadline;
import jack.task.Event;
import jack.task.ToDo;

public class TasksTest {
    @Test
    public void newTasksTest() {
        try {
            ToDo toDo = new ToDo("test");
            assertEquals("[T][ ] test", toDo.toString());
            toDo.mark();
            assertEquals("[T][X] test", toDo.toString());
            toDo.unmark();
            assertEquals("[T][ ] test", toDo.toString());


            // deadline
            Deadline deadline = new Deadline("deadline", "30/1/2026 1800");
            assertEquals("[D][ ] deadline (by: Jan 30 2026 6PM)", deadline.toString());
            deadline = new Deadline("deadline", "30/1/2026 1801");
            assertEquals("[D][ ] deadline (by: Jan 30 2026 6.01PM)", deadline.toString());

            // event
            Event event = new Event("event", "30/1/2026 1800", "30/1/2026 1801");
            assertEquals("[E][ ] event (from: Jan 30 2026 6PM to: Jan 30 2026 6.01PM)", event.toString());
        } catch (RuntimeException | Excep e) {
            throw new RuntimeException(e);
        }
    }
}
