package jack;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import jack.storage.Storage;
import jack.task.Task;
import jack.task.TaskList;
import jack.task.ToDo;

public class StorageTest {
    @Test
    public void saveTest() {
        String path = "./duke.txt";
        try {
            Storage storage = new Storage(path);
            TaskList list = storage.read();
            assertEquals(path + " is not empty", 0, list.size());
            list.add(new ToDo("test"));
            list.add(new ToDo("test 1"));
            storage.save(list);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            int lineCount = 0;
            String line;
            // Data Comparison
            while ((line = bufferedReader.readLine()) != null) {
                Task task = list.get(lineCount);
                String origin = Storage.serialize(task);
                assertEquals("Inconsistent data", origin, line);
                lineCount++;
            }
            // data count
            assertEquals(path + " line required 1", lineCount, list.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            File targetFile = new File(path);
            targetFile.delete();
        }
    }
}
