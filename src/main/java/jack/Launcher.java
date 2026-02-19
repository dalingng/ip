package jack;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.trim().equals("command")) {
                new Jack("./data/duke.txt").run();
                return;
            }
        }

        Application.launch(Main.class, args);
    }
}
