package jack;

import java.io.IOException;
import java.util.Scanner;

import jack.command.Command;
import jack.command.Parser;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

/**
 * The main class of the Jack application.
 * Jack is a task management application that allows users to add, delete, mark, and unmark tasks.
 * It also provides functionality to save tasks to a file and load them from a file.
 */
public class Jack {
    private final Ui ui;
    private Storage storage;


    /**
     * Constructs a new Jack instance with the specified file path for storage.
     * @param filePath The file path where tasks will be stored.
     */
    public Jack(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
        } catch (Excep | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs the Jack application.
     * This method initializes the UI, loads tasks from storage, and processes user commands.
     */
    public void run() {

        Scanner input = new Scanner(System.in);
        TaskList tasks = new TaskList();
        ui.showLine();
        ui.welcome();
        try {
            storage = new Storage("./data/duke.txt");
            tasks = storage.read();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        boolean isExis = false;

        while (!isExis) {
            String command = input.nextLine();
            try {
                Command cmd = Parser.parse(command);
                cmd.execute(tasks, ui, storage);
                isExis = cmd.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method of the Jack application.
     * It creates a new Jack instance and starts the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Jack("./data/duke.txt").run();
    }
}
