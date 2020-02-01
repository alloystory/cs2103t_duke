package app.models.tasks;

import app.models.DateTime;

public class EventTask extends Task {
    private DateTime when;

    public EventTask(String description, DateTime when) {
        super(description);
        this.when = when;
    }

    @Override
    public String toStorage() {
        return String.format("E,%s,%b,%s", this.description, this.isDone, this.when.toStorage());
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.when.toString());
    }
}