package jack.storage;

import jack.Excep;
import jack.task.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Save the tasks in the hard disk automatically whenever the task list changes. Load the data from the hard disk when the chatbot starts up. You may hard-code the file name and relative path from the project root e.g., ./data/duke.txt
 */
public class Storage {

    private static String separator = " | ";
    private File targetFile;
    public Storage(String path) throws Excep,IOException{

        targetFile = new File(path);

        if (!targetFile.exists()) {
            File parentDir = targetFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if(!parentDir.mkdirs()){
                    throw new IOException();
                }
            }

            boolean isCreated = targetFile.createNewFile();
            if (!isCreated) {
                throw new Excep("Create File Fail");
            }
        }
        if(!targetFile.isFile()){
            throw new Excep("Path is not a file");
        }
    }

    public void save(TaskList inputList) throws IOException{
        try{
            FileOutputStream os = new FileOutputStream(targetFile.getPath(), false); // clean and writer
            OutputStreamWriter ow = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(ow);
            for(int i = 0;i<inputList.size();i++){
                Task task = inputList.get(i);
                bw.write(serialize(task));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public TaskList read() throws FileNotFoundException,IOException{
        TaskList list = new TaskList();
        FileReader fileRead = new FileReader(targetFile.getPath());
        BufferedReader read = new BufferedReader(fileRead);
        String currentLine="";
        while ((currentLine = read.readLine()) != null){
            try{
                list.add(deserialize(currentLine));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Now you have " + list.size() + " tasks in the list.");
        return list;
    }

    private String serialize(Task task){
        return String.join(separator,task.taskName(),task.isDone()?"1":"0",task.toTask());
    }

    private Task deserialize(String line) throws Exception{
        String[] args = line.split(" \\| ");
        String type = args[0];
        boolean mark = args[1].equals("0")?false:true;
        String command = args[2];
        Task task;
        if(type.equals("E")){
            task = Event.taskToEvent(command);
        } else if (type.equals("D")) {
            task = Deadline.taskToDeadline(command);
        }else if (type.equals("T")) {
            task = ToDo.taskToToDo(command);
        } else{
            throw new Exception("unknown data type");
        }
        if(mark){
            task.mark();
        }
        return task;
    }
}
