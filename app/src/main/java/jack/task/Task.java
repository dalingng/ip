package jack.task;

public  class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone(){return isDone;}

    public  String taskName(){return "N";}

    public String getDescription(){return description;}

    public String toTask(){
        return this.getDescription();
    }

    public void delete() {

    }


}