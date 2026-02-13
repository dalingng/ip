package jack.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jack.Excep;
import jack.task.Deadline;
import jack.task.Event;

/**
 * Parses user input into appropriate Command objects.
 * This class is responsible for interpreting user commands and creating the corresponding command objects.
 */
public class Parser {
    /**
     * Parses the given string into a Command object.
     * @param str The user input string to parse.
     * @return The corresponding Command object based on the user input.
     * @throws Excep If the input is not a valid command or if there's an error parsing the command.
     */
    public static Command parse(String str) throws Excep {
        assert str != null : "Input string cannot be null";
        
        String regex = "^\\s*([a-zA-Z0-9_-]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            throw new Excep("unsupper command");
        }
        String tag = matcher.group(1);
        assert tag != null : "Command tag cannot be null";
        assert !tag.isEmpty() : "Command tag cannot be empty";
        
        // params string
        int endIndex = matcher.end();
        String remaining = str.substring(endIndex);
        String taskStr = remaining.trim();

        Command cmd;
        if (tag.equalsIgnoreCase("bye")) {
            cmd = new ByeCommand();
        } else if (tag.equalsIgnoreCase("list")) {
            cmd = new ListCommand();
        } else if (tag.equalsIgnoreCase("unmark")) {
            int idx = Integer.parseInt(taskStr);
            cmd = new UnmarkCommand(idx);
        } else if (tag.equalsIgnoreCase("mark")) {
            int idx = Integer.parseInt(taskStr);
            cmd = new MarkCommand(idx);
        } else if (tag.equalsIgnoreCase("delete")) {
            int idx = Integer.parseInt(taskStr);
            cmd = new DeleteCommand(idx);
        } else if (tag.equalsIgnoreCase("todo")) {
            cmd = new TodoCommand(taskStr);
        } else if (tag.equalsIgnoreCase("event")) {
            Event event = Event.taskToEvent(taskStr);
            cmd = new EventCommand(event);
        } else if (tag.equalsIgnoreCase("deadline")) {
            Deadline deadline = Deadline.taskToDeadline(taskStr);
            cmd = new DeadlineCommand(deadline);
        } else if (tag.equalsIgnoreCase("find")) {
            cmd = new FindCommand(taskStr);
        } else {
            throw new Excep("Wrong command");
        }
        return cmd;
    }
}
