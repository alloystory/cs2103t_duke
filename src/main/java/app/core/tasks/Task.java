package app.core.tasks;

import app.util.Date;
import app.exceptions.WrongDateTimeFormatException;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return this.description;
    }
    
    void setDone() {
        this.isDone = true;
    }
    
    public static Task fromStorage(String data) {
        try {
            String[] parsedArgs = data.split(",");
            String type = parsedArgs[0];

            Task output;
            if (type.equals("D")) {
                Date date = Date.fromStorage(parsedArgs[3]);
                output = new DeadlineTask(parsedArgs[1], date);
            } else if (type.equals("E")) {
                Date date = Date.fromStorage(parsedArgs[3]);
                output = new EventTask(parsedArgs[1], date);
            } else {
                output = new Task(parsedArgs[1]);
            }

            if (Boolean.parseBoolean(parsedArgs[2])) {
                output.setDone();
            }
            
            return output;
        } catch (WrongDateTimeFormatException e) {
            return null;
        }
    }
    
    public String toStorage() {
        return String.format("T,%s,%b", this.description, this.isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}