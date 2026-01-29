package jack.command;

import jack.Excep;
import jack.task.Deadline;
import jack.task.Event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String str) throws Excep {
        String regex = "^\\s*([a-zA-Z0-9_-]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            throw new Excep("unsupper command");
        }
        String tag = matcher.group(1);
        // params string
        int endIndex = matcher.end();
        String remaining = str.substring(endIndex);
        String taskStr = remaining.trim();

        Command cmd;
        if (tag.equalsIgnoreCase("bye")) {
            cmd = new ByeCommand();
        }else if (tag.equalsIgnoreCase("list")) {
            cmd = new ListCommand();
        }else if (tag.equalsIgnoreCase("unmark")) {
            int idx = Integer.parseInt(taskStr);
            cmd = new UnmarkCommand(idx);
        }else if (tag.equalsIgnoreCase("mark")) {
            int idx = Integer.parseInt(taskStr);
            cmd = new MarkCommand(idx);
        }else if (tag.equalsIgnoreCase("delete")) {
            int idx = Integer.parseInt(taskStr);
            cmd = new DeleteCommand(idx);
        }else if (tag.equalsIgnoreCase("todo")) {
            cmd = new TodoCommand(taskStr);
        }else if (tag.equalsIgnoreCase("event")) {
            Event event = Event.taskToEvent(taskStr);
            cmd = new EventCommand(event);
        }else if (tag.equalsIgnoreCase("deadline")) {
            Deadline deadline = Deadline.taskToDeadline(taskStr);
            cmd = new DeadlineCommand(deadline);
        }else{
            throw new Excep("Wrong command");
        }
        return cmd;
    }
}
