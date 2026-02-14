package jack.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jack.Excep;
import jack.task.Deadline;
import jack.task.Event;

/**
 * Parses user input into executable commands.
 * Handles command recognition and argument extraction.
 */
public class Parser {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\S+)\\s*");

    /**
     * Parses user input and returns the corresponding command.
     * @param userInput The user input string.
     * @return The parsed command.
     * @throws Excep If the command is invalid or arguments are missing.
     */
    public static Command parse(String userInput) throws Excep {
        assert userInput != null : "User input cannot be null";
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new Excep("unsupported command");
        }

        Matcher matcher = COMMAND_PATTERN.matcher(userInput);

        if (!matcher.find()) {
            throw new Excep("unsupported command");
        }
        String tag = matcher.group(1);
        assert tag != null : "Command tag cannot be null";
        assert !tag.isEmpty() : "Command tag cannot be empty";

        // params string
        int endIndex = matcher.end();
        String remaining = userInput.substring(endIndex);
        String commandArgs = remaining.trim();

        Command cmd;
        try {
            switch (tag.toLowerCase()) {
            case "bye":
                cmd = new ByeCommand();
                break;
            case "list":
                cmd = new ListCommand();
                break;
            case "unmark":
                cmd = new UnmarkCommand(parseTaskIndex(commandArgs));
                break;
            case "mark":
                cmd = new MarkCommand(parseTaskIndex(commandArgs));
                break;
            case "delete":
                cmd = new DeleteCommand(parseTaskIndex(commandArgs));
                break;
            case "todo":
                cmd = new TodoCommand(commandArgs);
                break;
            case "event":
                Event event = Event.taskToEvent(commandArgs);
                cmd = new EventCommand(event);
                break;
            case "deadline":
                Deadline deadline = Deadline.taskToDeadline(commandArgs);
                cmd = new DeadlineCommand(deadline);
                break;
            case "find":
                cmd = new FindCommand(commandArgs);
                break;
            case "remind":
                cmd = new ReminderCommand();
                break;
            case "undo":
                cmd = new UndoCommand();
                break;
            default:
                throw new Excep("Wrong command");
            }
        } catch (Excep e) {
            throw e;
        } catch (Exception e) {
            throw new Excep("Invalid command arguments: " + e.getMessage());
        }

        return cmd;
    }

    /**
     * Parses a task index from a string.
     * @param indexStr The string containing the index.
     * @return The parsed index.
     * @throws Excep If the index is invalid.
     */
    private static int parseTaskIndex(String indexStr) throws Excep {
        if (indexStr.isEmpty()) {
            throw new Excep("Task index cannot be empty");
        }

        try {
            int index = Integer.parseInt(indexStr);
            if (index < 1) {
                throw new Excep("Task index must be positive");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new Excep("Invalid task index: " + indexStr);
        }
    }
}
