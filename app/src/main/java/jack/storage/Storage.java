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
import jack.task.Task;
import jack.task.TaskList;
import jack.task.ToDo;

/**
 * Handles the storage of tasks in the Jack application.
 * Saves tasks to a file on the hard disk and loads them when the application starts.
 */
public class Storage {

    private static String separator = " | ";
    private File targetFile;
    /**
     * Constructs a new Storage instance with the specified file path.
     * Creates the file and parent directories if they do not exist.
     * @param path The file path where tasks will be stored.
     * @throws Excep If the file cannot be created or if the path is not a file.
     * @throws IOException If an I/O error occurs when creating directories.
     */
    public Storage(String path) throws Excep, IOException {
        targetFile = new File(path);

        if (!targetFile.exists()) {
            File parentDir = targetFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException();
                }
            }

            boolean isCreated = targetFile.createNewFile();
            if (!isCreated) {
                throw new Excep("Create File Fail");
            }
        }
        if (!targetFile.isFile()) {
            throw new Excep("Path is not a file");
        }
    }

    /**
     * Saves the specified task list to the storage file.
     * @param inputList The task list to save.
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void save(TaskList inputList) throws IOException {
        try {
            FileOutputStream os = new FileOutputStream(targetFile.getPath(), false); // clean and writer
            OutputStreamWriter ow = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(ow);
            for (int i = 0; i < inputList.size(); i++) {
                Task task = inputList.get(i);
                bw.write(serialize(task));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Reads tasks from the storage file and returns them as a TaskList.
     * @return A TaskList containing all tasks read from the file.
     * @throws FileNotFoundException If the storage file is not found.
     * @throws IOException If an I/O error occurs when reading from the file.
     */
    public TaskList read() throws FileNotFoundException, IOException {
        TaskList list = new TaskList();
        FileReader fileRead = new FileReader(targetFile.getPath());
        BufferedReader read = new BufferedReader(fileRead);
        String currentLine = "";
        while ((currentLine = read.readLine()) != null) {
            try {
                list.add(deserialize(currentLine));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Now you have " + list.size() + " tasks in the list.");
        return list;
    }

    /**
     * Serializes a Task object to a string format for storage.
     * @param task The task to serialize.
     * @return A string representation of the task in storage format.
     */
    public static String serialize(Task task) {
        return String.join(separator, task.taskName(), task.isDone() ? "1" : "0", task.toTask());
    }

    /**
     * Deserializes a string from the storage file to a Task object.
     * @param line The string to deserialize.
     * @return A Task object created from the string.
     * @throws Exception If the string format is invalid or the task type is unknown.
     */
    public static Task deserialize(String line) throws Exception {
        String[] args = line.split(" \\|");
        String type = args[0];
        boolean mark = args[1].equals("0") ? false : true;
        String command = args[2];
        Task task;
        if (type.equals("E")) {
            task = Event.taskToEvent(command);
        } else if (type.equals("D")) {
            task = Deadline.taskToDeadline(command);
        } else if (type.equals("T")) {
            task = ToDo.taskToToDo(command);
        } else {
            throw new Exception("unknown data type");
        }
        if (mark) {
            task.mark();
        }
        return task;
    }
}
