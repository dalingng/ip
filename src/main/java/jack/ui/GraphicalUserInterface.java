package jack.ui;


import jack.Excep;
import jack.command.Command;
import jack.command.Parser;
import jack.storage.Storage;
import jack.task.TaskList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents the graphical user interface for the Jack application.
 * Provides a JavaFX-based interface for user interaction.
 */
public class GraphicalUserInterface {
    private BorderPane mainContainer = new BorderPane();

    private VBox messageArea = new VBox(15);

    private TaskList tasks;

    private Ui ui;

    private Storage storage;

    private Stage stage;

    /**
     * Constructs a new GraphicalUserInterface with the specified stage, tasks, UI, and storage.
     * @param stage The primary stage for this GUI application.
     * @param tasks The task list to manage.
     * @param ui The user interface to display messages.
     * @param storage The storage to save tasks.
     */
    public GraphicalUserInterface(Stage stage, TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.stage = stage;
    }

    /**
     * Closes the GUI application.
     */
    public void close() {
        stage.close();
    }

    /**
     * Displays the GUI application.
     */
    public void show() {
        mainContainer.setStyle("-fx-background-color: #E8F5E8; -fx-padding: 10;");
        mainContainer.setPrefSize(400, 500);

        // Create scroll pane for message area
        ScrollPane scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: #E8F5E8; -fx-background-color: #E8F5E8;");
        mainContainer.setCenter(scrollPane);
        // Create input area
        HBox inputArea = new HBox(10);
        inputArea.setPadding(new Insets(10));
        TextField inputField = new TextField("");
        inputField.setStyle(
                "-fx-background-color: white; -fx-padding: 10; -fx-border-radius: 20; -fx-background-radius: 20;");
        Button sendButton = new Button("Send");

        // Add event handler for send button
        sendButton.setOnAction(event -> {
            String msg = inputField.getText();
            if (!msg.isEmpty()) {
                generateMessageBox(msg);
                try {
                    Command command = Parser.parse(msg);
                    String response = command.execute(tasks, ui, storage);
                    generateResponseMessageBox(response);
                    inputField.clear();
                    scrollToBottom();
                    if (command.isExit()) {
                        // Exit the application after 1 second
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                javafx.application.Platform.runLater(() -> {
                                    this.close();
                                });
                            }
                        }).start();
                    }
                } catch (Excep e) {
                    inputField.clear();
                    generateResponseMessageBox(e.getMessage());
                    scrollToBottom();
                } catch (Exception e) {
                    inputField.clear();
                    generateResponseMessageBox(e.getMessage());
                    scrollToBottom();
                }
            }
        });
        // Add event handler for enter key
        inputField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendButton.fire();
            }
        });

        sendButton.setMinWidth(80);
        sendButton.setStyle(
                "-fx-background-color: #4CAF50; "
                + "-fx-text-fill: white; "
                + "-fx-padding: 10 15; "
                + "-fx-border-radius: 20; "
                + "-fx-background-radius: 20;");
        inputArea.getChildren().addAll(inputField, sendButton);
        mainContainer.setBottom(inputArea);

        // Set up scene and stage
        Scene scene = new Scene(mainContainer);
        stage.setTitle("Jack");
        stage.setScene(scene);

        scene.widthProperty().addListener((obs, oldValue, newValue) -> {
            inputField.setPrefWidth(inputArea.getWidth() - sendButton.getWidth());
        });
        stage.show();
        generateResponseMessageBox(ui.welcome());
    }

    /**
     * Generates a message box for user input.
     * @param msg The user input message to display.
     */
    public void generateMessageBox(String msg) {
        HBox message = new HBox(10);
        Label sendMsg = new Label(msg);
        sendMsg.setWrapText(true);
        sendMsg.setMinHeight(Label.USE_PREF_SIZE);
        sendMsg.setStyle(
                "-fx-background-color: #A5D6A7; "
                + "-fx-text-fill: white; "
                + "-fx-padding: 5 10; "
                + "-fx-border-radius: 15; "
                + "-fx-background-radius: 15;");
        sendMsg.setFont(new Font(12));
        ImageView avatar2 = new ImageView(new Image(getClass().getResourceAsStream("/images/person.png")));
        avatar2.setFitWidth(60);
        avatar2.setFitHeight(60);
        HBox message2Right = new HBox();
        message2Right.setSpacing(10);
        message2Right.getChildren().addAll(sendMsg, avatar2);
        message.setAlignment(Pos.CENTER_RIGHT);
        message.getChildren().add(message2Right);
        messageArea.getChildren().add(message);
        scrollToBottom();
    }

    /**
     * Generates a message box for system responses.
     * @param msg The system response message to display.
     */
    public void generateResponseMessageBox(String msg) {
        // response message
        HBox message3 = new HBox(10);
        ImageView avatar3 = new ImageView(new Image(getClass().getResourceAsStream("/images/robot.png")));
        avatar3.setFitWidth(60);
        avatar3.setFitHeight(60);
        Label response = new Label(msg);
        response.setWrapText(true);
        response.setMinHeight(Label.USE_PREF_SIZE);
        response.setStyle(
                "-fx-background-color: #FFF59D; -fx-padding: 10; -fx-border-radius: 15; -fx-background-radius: 15;");
        response.setFont(new Font(14));
        message3.getChildren().addAll(avatar3, response);
        messageArea.getChildren().add(message3);
        scrollToBottom();
    }

    /**
     * Scrolls the message area to the bottom to show the latest messages.
     */
    private void scrollToBottom() {
        // Create a runnable to scroll to bottom after layout
        javafx.application.Platform.runLater(() -> {
            messageArea.layout();
            javafx.scene.control.ScrollPane scrollPane = (javafx.scene.control.ScrollPane) mainContainer.getCenter();
            scrollPane.setVvalue(1.0);
        });
    }
}
