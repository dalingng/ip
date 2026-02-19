package jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;

import org.junit.Test;

import jack.command.Command;
import jack.command.Parser;
import jack.command.TodoCommand;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.task.ToDo;
import jack.ui.Ui;

/**
 * Tests for command parsing and execution.
 */
public class CommandTest {
    @Test
    public void testTodoCommandExecution() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./test_duke.txt");

        try {
            TodoCommand command = new TodoCommand(new ToDo("test task"));
            String result = command.execute(tasks, ui, storage);

            assertEquals(1, tasks.size());
            assertEquals("test task", tasks.get(0).getDescription());
        } finally {
            // Clean up: delete test data file
            File testFile = new File("./test_duke.txt");
            if (testFile.exists()) {
                testFile.delete();
            }
        }
    }

    @Test
    public void testParserWithValidTodoCommand() throws Exception {
        Command command = Parser.parse("todo test task");
        assertEquals(TodoCommand.class, command.getClass());
    }

    @Test
    public void testTodoCommandWithEmptyTask() {
        Excep exception = assertThrows(Excep.class, () -> {
            Parser.parse("todo ");
        });
        assertEquals("todo description cannot be empty", exception.getMessage());
    }

    @Test
    public void testParserWithInvalidCommand() {
        Excep exception = assertThrows(Excep.class, () -> {
            Parser.parse("invalid command");
        });
        assertEquals("Wrong command", exception.getMessage());
    }

    @Test
    public void testParserWithEmptyInput() {
        Excep exception = assertThrows(Excep.class, () -> {
            Parser.parse("");
        });
        assertEquals("empty command", exception.getMessage());
    }

    @Test
    public void testTaskListAddAndRemove() {
        TaskList tasks = new TaskList();

        // Test adding tasks
        tasks.add(new ToDo("task 1"));
        tasks.add(new ToDo("task 2"));
        assertEquals(2, tasks.size());

        // Test removing tasks
        tasks.remove(0);
        assertEquals(1, tasks.size());
        assertEquals("task 2", tasks.get(0).getDescription());
    }

    @Test
    public void testTaskMarkAndUnmark() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("test task");
        tasks.add(todo);

        // Test marking task as done
        todo.mark();
        assertEquals(true, todo.isDone());

        // Test unmarking task
        todo.unmark();
        assertEquals(false, todo.isDone());
    }
}
