package jack.ui;


import jack.Excep;
import jack.command.Command;
import jack.command.Parser;
import jack.storage.Storage;
import jack.task.TaskList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the graphical user interface for the Jack application.
 * Provides a JavaFX-based interface for user interaction.
 * AI-assisted: Cursor helped improve the UI design with better colors and layout.
 */
public class GraphicalUserInterface {
    private static final String PRIMARY_COLOR = "#6366F1";
    private static final String PRIMARY_LIGHT = "#818CF8";
    private static final String SECONDARY_COLOR = "#10B981";
    private static final String BG_COLOR = "#F8FAFC";
    private static final String SURFACE_COLOR = "#FFFFFF";
    private static final String TEXT_PRIMARY = "#1E293B";
    private static final String TEXT_SECONDARY = "#64748B";

    private BorderPane mainContainer = new BorderPane();
    private VBox messageArea = new VBox(12);
    private ScrollPane scrollPane;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Stage stage;
    private TextField inputField;

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
     * Displays the GUI application with improved UI design.
     */
    public void show() {
        mainContainer.setStyle("-fx-background-color: " + BG_COLOR + ";");
        mainContainer.setPrefSize(450, 650);

        VBox header = createHeader();
        mainContainer.setTop(header);

        scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: " + BG_COLOR + "; -fx-background-color: transparent; "
                + "-fx-border-width: 0; -fx-padding: 0;");
        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
        });

        VBox contentContainer = new VBox(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        contentContainer.setStyle("-fx-background-color: " + BG_COLOR + ";");
        contentContainer.setPadding(new Insets(0, 12, 0, 12));
        mainContainer.setCenter(contentContainer);

        HBox inputArea = createInputArea();
        mainContainer.setBottom(inputArea);

        Scene scene = new Scene(mainContainer);
        scene.setFill(Color.web(BG_COLOR));

        stage.setTitle("Jack - Your Personal Task Assistant");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/robot.png")));
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(500);

        messageArea.heightProperty().addListener(obs -> scrollToBottom());

        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            generateResponseMessageBox(ui.welcome());
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.setStyle("-fx-background-color: " + PRIMARY_COLOR + "; -fx-padding: 16;");
        header.setEffect(new DropShadow(4, 0, 2, Color.rgb(0, 0, 0, 0.15)));

        HBox headerContent = new HBox(12);
        headerContent.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/images/robot.png")));
        logo.setFitHeight(40);
        logo.setFitWidth(40);
        logo.setClip(new Circle(20, 20, 20));

        VBox titleBox = new VBox(2);
        Label title = new Label("Jack");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label("Your Personal Task Assistant");
        subtitle.setFont(Font.font("Segoe UI", 12));
        subtitle.setTextFill(Color.web("#C7D2FE"));

        titleBox.getChildren().addAll(title, subtitle);
        headerContent.getChildren().addAll(logo, titleBox);

        header.getChildren().add(headerContent);
        return header;
    }

    private HBox createInputArea() {
        HBox inputArea = new HBox(10);
        inputArea.setPadding(new Insets(12, 12, 12, 12));
        inputArea.setStyle("-fx-background-color: " + SURFACE_COLOR + ";");
        inputArea.setAlignment(Pos.CENTER);

        inputField = new TextField();
        inputField.setPromptText("Type your message...");
        inputField.setPrefHeight(42);
        inputField.setFont(Font.font("Segoe UI", 14));
        inputField.setStyle("-fx-background-color: #F1F5F9; -fx-padding: 10 16; "
                + "-fx-border-radius: 21; -fx-background-radius: 21; "
                + "-fx-text-fill: " + TEXT_PRIMARY + "; -fx-prompt-text-fill: " + TEXT_SECONDARY + ";");
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Button sendButton = new Button("Send");
        sendButton.setMinSize(80, 42);
        sendButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        sendButton.setStyle("-fx-background-color: " + PRIMARY_COLOR + "; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-border-radius: 21; -fx-background-radius: 21; "
                + "-fx-cursor: hand;");
        sendButton.setOnMouseEntered(e -> sendButton.setStyle(
                "-fx-background-color: " + PRIMARY_LIGHT + "; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-border-radius: 21; -fx-background-radius: 21;"));
        sendButton.setOnMouseExited(e -> sendButton.setStyle(
                "-fx-background-color: " + PRIMARY_COLOR + "; -fx-text-fill: white; "
                + "-fx-padding: 10 20; -fx-border-radius: 21; -fx-background-radius: 21;"));

        sendButton.setOnAction(event -> handleSend());
        inputField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSend();
            }
        });

        inputArea.getChildren().addAll(inputField, sendButton);
        return inputArea;
    }

    private void handleSend() {
        String msg = inputField.getText();
        if (!msg.isEmpty()) {
            generateMessageBox(msg);
            try {
                Command command = Parser.parse(msg);
                String response = command.execute(tasks, ui, storage);
                generateResponseMessageBox(response);
                inputField.clear();
                if (command.isExit()) {
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            javafx.application.Platform.runLater(() -> this.close());
                        }
                    }).start();
                }
            } catch (Excep e) {
                inputField.clear();
                generateResponseMessageBox(ui.getFriendlyError(e.getMessage()));
            }
        }
    }

    /**
     * Generates a styled message bubble for user input.
     * @param msg The user input message to display.
     */
    public void generateMessageBox(String msg) {
        HBox messageRow = new HBox();
        messageRow.setAlignment(Pos.CENTER_RIGHT);
        messageRow.setPadding(new Insets(4, 0, 4, 40));

        Label messageLabel = new Label(msg);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(280);
        messageLabel.setFont(Font.font("Segoe UI", 14));
        messageLabel.setStyle("-fx-background-color: " + PRIMARY_COLOR + "; -fx-text-fill: white; "
                + "-fx-padding: 12 16; -fx-border-radius: 18 4 18 18; -fx-background-radius: 18 4 18 18;");
        messageLabel.setEffect(new DropShadow(3, 0, 1, Color.rgb(0, 0, 0, 0.1)));

        ImageView avatar = createCircularAvatar("/images/person.png");
        messageRow.getChildren().addAll(messageLabel, avatar);

        HBox.setMargin(avatar, new Insets(0, 0, 0, 8));

        messageArea.getChildren().add(messageRow);
        scrollToBottom();
    }

    /**
     * Generates a styled message bubble for system responses.
     * @param msg The system response message to display.
     */
    public void generateResponseMessageBox(String msg) {
        HBox messageRow = new HBox(8);
        messageRow.setAlignment(Pos.CENTER_LEFT);
        messageRow.setPadding(new Insets(4, 40, 4, 0));

        ImageView avatar = createCircularAvatar("/images/robot.png");
        Label messageLabel = new Label(msg);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(280);
        messageLabel.setFont(Font.font("Segoe UI", 14));
        messageLabel.setStyle("-fx-background-color: " + SURFACE_COLOR + "; -fx-text-fill: " + TEXT_PRIMARY + "; "
                + "-fx-padding: 12 16; -fx-border-radius: 4 18 18 18; -fx-background-radius: 4 18 18 18;");
        messageLabel.setEffect(new DropShadow(3, 0, 1, Color.rgb(0, 0, 0, 0.08)));

        messageRow.getChildren().addAll(avatar, messageLabel);
        messageArea.getChildren().add(messageRow);
        scrollToBottom();
    }

    private ImageView createCircularAvatar(String resourcePath) {
        ImageView avatar = new ImageView(new Image(getClass().getResourceAsStream(resourcePath)));
        avatar.setFitHeight(36);
        avatar.setFitWidth(36);
        Circle clip = new Circle(18, 18, 18);
        avatar.setClip(clip);
        return avatar;
    }

    private void scrollToBottom() {
        javafx.application.Platform.runLater(() -> {
            if (scrollPane != null) {
                scrollPane.layout();
                scrollPane.setVvalue(1.0);
            }
        });
    }
}
