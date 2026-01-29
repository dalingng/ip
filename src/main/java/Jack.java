import java.util.ArrayList;
import java.util.Scanner;

public class Jack {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> inputList = new ArrayList<>();
        System.out.println(line);
        welcome();
        TaskData taskData;
        try{
            taskData = new TaskData("./data/duke.txt");
            inputList = taskData.read();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        while (true) {
            String command = input.nextLine();
            try {
                if (command.equals("bye")) {
                    bye();
                    break;
                } else if (command.equals("list")) {
                    list(inputList);
                } else if (command.startsWith("unmark ")) {
                    int idx = Integer.parseInt(command.substring(7)) - 1;
                    if (idx < 0 || idx >= inputList.size()) {
                        throw new Excep("no such task number");
                    }
                    Task t = inputList.get(idx);
                    unmark(t);
                } else if (command.startsWith("mark ")) {
                    int idx = Integer.parseInt(command.substring(5)) - 1;
                    if (idx < 0 || idx >= inputList.size()) {
                        throw new Excep("no such task number");
                    }
                    Task t = inputList.get(idx);
                    mark(t);
                    taskData.save(inputList);
                } else if (command.startsWith("todo ")) {
                    String text = command.substring(5);
                    ToDo todo = ToDo.taskToToDo(text);
                    inputList = todo(inputList, todo);
                    taskData.save(inputList);
                } else if (command.startsWith("event ")) {
                    String task = command.substring(6);
                    Event e = Event.taskToEvent(task);
                    inputList = event(inputList, e);
                    taskData.save(inputList);
                } else if (command.startsWith("deadline ")) {
                    String task = command.substring(9);
                    Deadline deadline = Deadline.taskToDeadline(task);
                    inputList = deadline(inputList, deadline);
                    taskData.save(inputList);
                } else if (command.startsWith("delete ")) {
                    int idx = Integer.parseInt(command.substring(7)) - 1;
                    if (idx < 0 || idx >= inputList.size()) {
                        throw new Excep("no such task number");
                    }
                    Task t = inputList.get(idx);
                    inputList.remove(idx);
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                    System.out.println(line);
                    taskData.save(inputList);
                } else {
                    throw new Excep("idk your command");
                }

            } catch (Excep e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }catch (Exception e){
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static ArrayList<Task> todo(ArrayList<Task> list, ToDo todo) {
        list.add(todo);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println(line);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        return list;
    }

    private static ArrayList<Task> deadline(ArrayList<Task> list, Deadline deadline) {
        list.add(deadline);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println(line);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        return list;
    }

    private static ArrayList<Task> event(ArrayList<Task> list, Event event) {
        list.add(event);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println(line);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        return list;
    }

    private static void welcome() {
        System.out.println(line);
        System.out.println("Hello I'm Jack");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /*private static ArrayList<Task> add(ArrayList<Task> list, String c) {
        System.out.println(line);
        System.out.println("added: " + c);
        System.out.println(line);
        list[list.size()] = new Task(c);
        return list;
    }*/
    private static void list(ArrayList<Task> list) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int j = 1; j <= list.size(); j++) {
            System.out.println(j + "." + list.get(j - 1).toString());
        }
        System.out.println(line);
    }
    private static void mark(Task t) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        t.mark();
        System.out.println("  " + t);
        System.out.println(line);
    }

    private static void unmark(Task t) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        t.unmark();
        System.out.println("  " + t);
        System.out.println(line);
    }

}