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
     * @param userInput The user input string to parse.
     * @return The corresponding Command object based on the user input.
     * @throws Excep If the input is not a valid command or if there's an error parsing the command.
     */
    public static Command parse(String userInput) throws Excep {
        assert userInput != null : "Input string cannot be null";
        String regex = "^\\s*([a-zA-Z0-9_-]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);

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
            default:
                throw new Excep("Wrong command");
            }
        } catch (NumberFormatException e) {
            throw new Excep("Invalid task index. Please provide a valid number.");
        }

        return cmd;
    }

    /**
     * Parses the task index from the command arguments.
     * @param args The command arguments containing the task index.
     * @return The parsed task index.
     * @throws NumberFormatException If the arguments do not contain a valid number.
     */
    private static int parseTaskIndex(String args) {
        return Integer.parseInt(args);
    }
}
