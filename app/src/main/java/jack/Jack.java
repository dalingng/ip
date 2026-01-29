package jack;
import jack.Excep;
import jack.command.Command;
import jack.command.Parser;
import jack.storage.Storage;
import jack.task.TaskList;
import jack.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Jack {
    private final Ui ui;
    private Storage storage;
    public  Jack(String filePath){
        this.ui = new Ui();
        try{
            this.storage = new Storage(filePath);
        } catch (Excep | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){

        Scanner input = new Scanner(System.in);
        TaskList tasks = new TaskList();
        ui.showLine();
        ui.welcome();
        try{
            storage = new Storage("./data/duke.txt");
            tasks = storage.read();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        boolean isExis = false;

        while (!isExis) {
            String command = input.nextLine();
            try {
                Command cmd = Parser.parse(command);
                cmd.execute(tasks,ui,storage);
                isExis = cmd.isExit();
            }catch (Exception e){
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Jack("./data/duke.txt").run();
    }

}