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
            } else if (command.startsWith("unmark")) {
                int idx = Integer.parseInt(command.substring(7)) - 1;
                Task t = inputList[idx];
                unmark(t);
            } else if (command.startsWith("mark")) {
                int idx = Integer.parseInt(command.substring(5)) - 1;
                Task t = inputList[idx];
                mark(t);
            } else {
                inputList = add(inputList, count, command);
                count = count + 1;
            }
        }
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