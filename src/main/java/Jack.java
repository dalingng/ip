import java.util.Scanner;

public class Jack {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] inputList = new Task[100];
        int count = 0;
        System.out.println(line);
        welcome();
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                bye();
                break;
            } else if (command.equals("list")) {
                list(inputList, count);
            } else if (command.startsWith("unmark ")) {
                int idx = Integer.parseInt(command.substring(7)) - 1;
                Task t = inputList[idx];
                unmark(t);
            } else if (command.startsWith("mark ")) {
                int idx = Integer.parseInt(command.substring(5)) - 1;
                Task t = inputList[idx];
                mark(t);


            } else if (command.startsWith("todo ")) {
                String text = command.substring(5);
                inputList = todo(inputList, count, text);
                count = count + 1;
            } else if (command.startsWith("event ")) {
                String task = command.substring(6);
                String[] temp = task.split(" /", 3);
                String text = temp[0];
                String start = temp[1];
                String end = temp[2];
                inputList = event(inputList, count, text, start, end);
                count = count + 1;
            } else if (command.startsWith("deadline ")) {
                String task = command.substring(9);
                String[] temp = task.split(" /", 2);
                String text = temp[0];
                String dl = temp[1];
                inputList = deadline(inputList, count, text, dl);
                count = count + 1;
            } else {
                inputList = add(inputList, count, command);
                count = count + 1;
            }
        }
    }

    private static Task[] todo(Task[] list, int count, String c) {
        ToDo todo = new ToDo(c);
        list[count] = todo;
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println(line);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
        return list;
    }

    private static Task[] deadline(Task[] list, int count, String c, String d) {
        Deadline deadline = new Deadline(c, d);
        list[count] = deadline;
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println(line);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
        return list;
    }

    private static Task[] event(Task[] list, int count, String c, String s, String e) {
        Event event = new Event(c, s, e);
        list[count] = event;
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

    private static Task[] add(Task[] list, int count, String c) {
        System.out.println(line);
        System.out.println("added: " + c);
        System.out.println(line);
        list[count] = new Task(c);
        return list;
    }
    private static void list(Task[] list, int count) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int j = 1; j <= count; j++) {
            System.out.println(j + "." + list[j - 1].toString());
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