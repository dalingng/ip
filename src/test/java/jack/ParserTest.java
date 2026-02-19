package jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jack.command.ByeCommand;
import jack.command.Command;
import jack.command.DeadlineCommand;
import jack.command.DeleteCommand;
import jack.command.EventCommand;
import jack.command.FindCommand;
import jack.command.ListCommand;
import jack.command.ListNotesCommand;
import jack.command.MarkCommand;
import jack.command.NoteCommand;
import jack.command.Parser;
import jack.command.ReminderCommand;
import jack.command.TodoCommand;
import jack.command.UndoCommand;
import jack.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void parseByeCommand() throws Excep {
        Command cmd = Parser.parse("bye");
        assertEquals(ByeCommand.class, cmd.getClass());
    }

    @Test
    public void parseListCommand() throws Excep {
        Command cmd = Parser.parse("list");
        assertEquals(ListCommand.class, cmd.getClass());
    }

    @Test
    public void parseTodoCommand() throws Excep {
        Command cmd = Parser.parse("todo read book");
        assertEquals(TodoCommand.class, cmd.getClass());
    }

    @Test
    public void parseDeadlineCommand() throws Excep {
        Command cmd = Parser.parse("deadline return book /by 30/1/2026 1800");
        assertEquals(DeadlineCommand.class, cmd.getClass());
    }

    @Test
    public void parseEventCommand() throws Excep {
        Command cmd = Parser.parse("event meeting /from 30/1/2026 1800 /to 30/1/2026 2000");
        assertEquals(EventCommand.class, cmd.getClass());
    }

    @Test
    public void parseMarkCommand() throws Excep {
        Command cmd = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, cmd.getClass());
    }

    @Test
    public void parseUnmarkCommand() throws Excep {
        Command cmd = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, cmd.getClass());
    }

    @Test
    public void parseDeleteCommand() throws Excep {
        Command cmd = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, cmd.getClass());
    }

    @Test
    public void parseFindCommand() throws Excep {
        Command cmd = Parser.parse("find book");
        assertEquals(FindCommand.class, cmd.getClass());
    }

    @Test
    public void parseRemindCommand() throws Excep {
        Command cmd = Parser.parse("remind");
        assertEquals(ReminderCommand.class, cmd.getClass());
    }

    @Test
    public void parseUndoCommand() throws Excep {
        Command cmd = Parser.parse("undo");
        assertEquals(UndoCommand.class, cmd.getClass());
    }

    @Test
    public void parseNoteCommand() throws Excep {
        Command cmd = Parser.parse("note this is a note");
        assertEquals(NoteCommand.class, cmd.getClass());
    }

    @Test
    public void parseNotesCommand() throws Excep {
        Command cmd = Parser.parse("notes");
        assertEquals(ListNotesCommand.class, cmd.getClass());
    }

    @Test
    public void parseCommandCaseInsensitive() throws Excep {
        Command cmd1 = Parser.parse("BYE");
        Command cmd2 = Parser.parse("Bye");
        assertEquals(ByeCommand.class, cmd1.getClass());
        assertEquals(ByeCommand.class, cmd2.getClass());
    }

    @Test
    public void parseCommandWithExtraSpaces() throws Excep {
        Command cmd = Parser.parse("todo   test   ");
        assertEquals(TodoCommand.class, cmd.getClass());
    }

}
