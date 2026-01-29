package jack.ui;

import jack.task.*;

import java.util.ArrayList;

public class Ui {
    private static final String line = "____________________________________________________________";
    public void showLine(){
        System.out.println(line);
    }

    public void todo(TaskList list, ToDo todo) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todo);
        System.out.println(line);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void deadline(TaskList list, Deadline deadline) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println(line);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void event(TaskList list, Event event) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println(line);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
    public void delete(TaskList list, Task t){
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void welcome() {
        System.out.println(line);
        System.out.println("Hello I'm Jack");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void list(TaskList list) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int j = 1; j <= list.size(); j++) {
            System.out.println(j + "." + list.get(j - 1).toString());
        }
        System.out.println(line);
    }
    public void mark() {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
    }
    public void markSuccess(Task t){
        System.out.println("  " + t);
        System.out.println(line);
    }

    public void unmark() {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
    }
    public void unmarkSuccess(Task t) {

        System.out.println("  " + t);
        System.out.println(line);
    }

    public void showError(String massage){
        System.out.println(line);
        System.out.println(massage);
        System.out.println(line);
    }

}
