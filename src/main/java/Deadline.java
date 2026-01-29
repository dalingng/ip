import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

public class Deadline extends Task {

    protected String due;


    public Deadline(String description, String due) throws Excep {
        super(description);
        try{
            LocalDateTime time = DateTimeTool.parseDateTime(due);
            this.due = time.format(DateTimeTool.getTimeFormat());
        }catch (Excep e){
            System.out.println(e.getMessage());
            this.due = due;
        }
    }



    @Override
    public String toString() {
        return "["+taskName()+"]" + super.toString() + " (by: " + due + ")";
    }

    @Override
    public String taskName(){return "D";}

    public String getDue(){return due;}


    public static Deadline taskToDeadline(String task) throws Excep{
        if (task.isEmpty()) {
            throw new Excep("no deadline i also want");
        } else if (!task.contains("by")) {
            throw new Excep("wrong format");
        }
        String[] temp = task.split(" /", 2);
        String text = temp[0];
        String tempdl = temp[1];
        String dl = tempdl.substring(3);
        return new Deadline(text,dl);
    }

    public String toTask(){
        return this.getDescription() + " /by "+this.getDue();
    }

}