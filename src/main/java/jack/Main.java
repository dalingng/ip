package jack;

import java.io.IOException;

import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.GraphicalUserInterface;
import jack.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class for the Jack GUI application.
 * Extends Application to provide JavaFX functionality.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            TaskList tasks = new TaskList();
            Storage storage = new Storage("./data/duke.txt");
            tasks = storage.read();

            GraphicalUserInterface gui = new GraphicalUserInterface(stage, tasks, new Ui(), storage);
            gui.show();
        } catch (Excep e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
