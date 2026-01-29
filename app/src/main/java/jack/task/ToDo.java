package jack.task;

import jack.Excep;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "["+taskName()+"]" + super.toString();
    }

    @Override
    public String taskName(){return "T";}

    public String serialize(){
        return String.join(" | ",this.isDone?"1":"0",this.description);
    }

    public static ToDo taskToToDo(String text) throws Excep {

        if (text.isEmpty()) {
            throw new Excep("nothing todo i also want");
        }
        return new ToDo(text);
    }
}