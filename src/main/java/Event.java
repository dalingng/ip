import java.time.LocalDateTime;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        try{
            LocalDateTime startTime = DateTimeTool.parseDateTime(start);
            this.start = DateTimeTool.formatDateTime(startTime);


            LocalDateTime endTime = DateTimeTool.parseDateTime(start);
            this.end = DateTimeTool.formatDateTime(endTime);
        }catch (Excep e){
            System.out.println(e.getMessage());
            this.start = start;
            this.end = end;
        }
    }


    @Override
    public String toString() {return "["+taskName()+"]" + super.toString() + " (from: " + start + " to: " + end + ")";}

    @Override
    public String taskName(){return "E";}

    public static Event taskToEvent(String task) throws Excep{
        if (task.isEmpty()) {
            throw new Excep("no event i also want");
        } else if (!task.contains("from") || (!task.contains("to"))) {
            throw new Excep("wrong format");
        }
        String[] temp = task.split(" /", 3);
        String text = temp[0];
        String tempstart = temp[1];
        String start = tempstart.substring(5);
        String tempend = temp[2];
        String end = tempend.substring(3);
        Event t = new Event(text,start,end);
        return t;
    }

    public String toTask(){
        return this.getDescription() + " /from "+this.getStart()+" /to "+this.getEnd();
    }

    public String getStart(){return start;}
    public String getEnd(){return end;}
}