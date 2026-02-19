package jack.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import jack.Excep;
import jack.task.Deadline;
import jack.task.Event;
import jack.task.Note;
import jack.task.Task;
import jack.task.TaskList;
import jack.task.ToDo;

/**
 * Handles the storage of tasks in the Jack application.
 * Saves tasks to a file on the hard disk and loads them when the application starts.
 */
public class Storage {

    private static final String SEPARATOR = " | ";
    private File storageFile;

    /**
     * Constructs a new Storage instance with the specified file path.
     * Creates the file and parent directories if they do not exist.
     * @param filePath The file path where tasks will be stored.
     * @throws Excep If the file cannot be created or if the path is not a file.
     * @throws IOException If an I/O error occurs when creating directories.
     */
    public Storage(String path) throws Excep, IOException {
        assert path != null : "File path cannot be null";
        assert !path.isEmpty() : "File path cannot be empty";

        storageFile = new File(path);

        if (!storageFile.exists()) {
            File parentDir = storageFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException();
                }
            }

            boolean isCreated = storageFile.createNewFile();
            if (!isCreated) {
                throw new Excep("Create File Fail");
            }
        }

        storageFile = new File(path);

        if (!storageFile.exists()) {
            createStorageFile();
        } else if (!storageFile.isFile()) {
            throw new Excep("Path is not a file");
        }
    }

    /**
     * Creates the storage file and parent directories if they do not exist.
     * @throws IOException If an I/O error occurs when creating directories or file.
     * @throws Excep If the file cannot be created.
     */
    private void createStorageFile() throws IOException, Excep {
        File parentDir = storageFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create parent directories");
            }
        }

        boolean isCreated = storageFile.createNewFile();
        if (!isCreated) {
            throw new Excep("Could not create data file");
        }
    }

    /**
     * Saves the specified task list to the storage file.
     * @param taskList The task list to save.
     * @throws Excep If an I/O error occurs when writing to the file.
     */
    public void save(TaskList taskList) throws Excep {
        assert taskList != null : "Task list cannot be null";
        assert storageFile != null : "Target file cannot be null";
        if (taskList == null) {
            throw new Excep("Task list cannot be null");
        }

        try (FileOutputStream os = new FileOutputStream(storageFile.getPath(), false);
             OutputStreamWriter ow = new OutputStreamWriter(os, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(ow)) {

            for (Task task : taskList) {
                assert task != null : "Task cannot be null";
                if (task != null) {
                    bw.write(serialize(task));
                    bw.newLine();
                }
            }

            bw.flush();
        } catch (IOException e) {
            throw new Excep("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the storage file and returns them as a TaskList.
     * @return A TaskList containing all tasks read from the file.
     * @throws FileNotFoundException If the storage file is not found.
     * @throws IOException If an I/O error occurs when reading from the file.
     */
    public TaskList read() throws Excep {
        TaskList taskList = new TaskList();

        try (FileReader fileReader = new FileReader(storageFile.getPath());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                try {
                    Task task = deserialize(currentLine);
                    if (task != null) {
                        taskList.add(task);
                    }
                } catch (Exception e) {
                    System.err.println("Error reading task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tasks: " + e.getMessage());
            throw new Excep("Error reading tasks: " + e.getMessage());
        }

        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        return taskList;
    }

    /**
     * Serializes a Task object to a string format for storage.
     * @param task The task to serialize.
     * @return A string representation of the task in storage format.
     * @throws Excep If the task is null.
     */
    public static String serialize(Task task) throws Excep {
        if (task == null) {
            throw new Excep("Task cannot be null");
        }
        String taskString = task.toTask().replace(" | ", "@|@");
        return String.join(SEPARATOR, task.taskName(), task.isDone() ? "1" : "0", taskString);
    }

    /**
     * Deserializes a string from the storage file to a Task object.
     * @param line The string to deserialize.
     * @return A Task object created from the string.
     * @throws Excep If the string format is invalid or the task type is unknown.
     */
    public static Task deserialize(String line) throws Excep {
        assert line != null : "Line cannot be null";
        assert !line.isEmpty() : "Line cannot be empty";

        String[] args = line.split(" \\| ");
        assert args.length >= 3 : "Line must have at least 3 parts: type | status | command";

        String type = args[0];
        assert type != null : "Task type cannot be null";
        assert !type.isEmpty() : "Task type cannot be empty";

        String statusStr = args[1];
        assert statusStr != null : "Status string cannot be null";
        assert statusStr.trim().equals("0") || statusStr.trim().equals("1") : "Status must be '0' or '1'";

        boolean mark = statusStr.trim().equals("0") ? false : true;
        String command = args[2].trim().replaceAll("@|@", " | ");
        assert command != null : "Command string cannot be null";
        assert !command.isEmpty() : "Command string cannot be empty";

        Task task;
        switch (type) {
        case "E":
            task = Event.taskToEvent(command);
            break;
        case "D":
            task = Deadline.taskToDeadline(command);
            break;
        case "T":
            task = ToDo.taskToToDo(command);
            break;
        case "N":
            task = Note.taskToNote(command);
            break;
        default:
            throw new Excep("Unknown task type: " + type);
        }
        assert task != null : "Created task cannot be null";

        if (mark) {
            task.mark();
        }

        return task;
    }
}
