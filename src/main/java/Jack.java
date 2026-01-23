import java.util.ArrayList;
import java.util.Scanner;

public class Jack {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> inputList = new ArrayList<>();
        int count = 0;
        System.out.println(line);
        welcome();
        while (true) {
            String command = input.nextLine();
            try {
                if (command.equals("bye")) {
                    bye();
                    break;
                } else if (command.equals("list")) {
                    list(inputList, count);
                } else if (command.startsWith("unmark ")) {
                    int idx = Integer.parseInt(command.substring(7)) - 1;
                    if (idx < 0 || idx >= count) {
                        throw new Excep("no such task number");
                    }
                    Task t = inputList.get(idx);
                    unmark(t);
                } else if (command.startsWith("mark ")) {
                    int idx = Integer.parseInt(command.substring(5)) - 1;
                    if (idx < 0 || idx >= count) {
                        throw new Excep("no such task number");
                    }
                    Task t = inputList.get(idx);
                    mark(t);


                } else if (command.startsWith("todo ")) {
                    String text = command.substring(5);
                    if (text.isEmpty()) {
                        throw new Excep("nothing todo i also want");
                    }
                    inputList = todo(inputList, count, text);
                    count = count + 1;
                } else if (command.startsWith("event ")) {
                    String task = command.substring(6);
                    if (task.isEmpty()) {
                        throw new Excep("no event i also want");
                    } else if (!task.contains("from") || (!task.contains("to"))) {
                        throw new Excep("wrong format");
                    }
                    String[] temp = task.split(" /", 3);
                    String text = temp[0];
                    String tempstart = temp[1];
                    String start = tempstart.substring(5);
                    String tempend = temp[2];
                    String end = tempend.substring(3);
                    inputList = event(inputList, count, text, start, end);
                    count = count + 1;

                } else if (command.startsWith("deadline ")) {
                    String task = command.substring(9);
                    if (task.isEmpty()) {
                        throw new Excep("no deadline i also want");
                    } else if (!task.contains("by")) {
                        throw new Excep("wrong format");
                    }
                    String[] temp = task.split(" /", 2);
                    String text = temp[0];
                    String tempdl = temp[1];
                    String dl = tempdl.substring(3);
                    inputList = deadline(inputList, count, text, dl);
                    count = count + 1;
                } else if (command.startsWith("delete ")) {
                    int idx = Integer.parseInt(command.substring(7)) - 1;
                    if (idx < 0 || idx >= count) {
                        throw new Excep("no such task number");
                    }
                    Task t = inputList.get(idx);
                    inputList.remove(idx);
                    count = count - 1;
                    System.out.println(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    System.out.println(line);
                } else {
                    throw new Excep("idk your command");
                }
            } catch (Excep e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static ArrayList<Task> todo(ArrayList<Task> list, int count, String c) {
        ToDo todo = new ToDo(c);
        list.add(todo);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println(line);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
        return list;
    }

    private static ArrayList<Task> deadline(ArrayList<Task> list, int count, String c, String d) {
        Deadline deadline = new Deadline(c, d);
        list.add(deadline);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println(line);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
        return list;
    }

    private static ArrayList<Task> event(ArrayList<Task> list, int count, String c, String s, String e) {
        Event event = new Event(c, s, e);
        list.add(event);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println(line);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
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

    /*private static ArrayList<Task> add(ArrayList<Task> list, int count, String c) {
        System.out.println(line);
        System.out.println("added: " + c);
        System.out.println(line);
        list[count] = new Task(c);
        return list;
    }*/
    private static void list(ArrayList<Task> list, int count) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int j = 1; j <= count; j++) {
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