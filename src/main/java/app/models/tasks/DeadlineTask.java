package app.models.tasks;

import app.models.DateTime;

public class DeadlineTask extends Task {
    private DateTime deadline;

    public DeadlineTask(String description, DateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toStorage() {
        return String.format("D,%s,%b,%s", this.description, this.isDone, this.deadline.toStorage());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.deadline.toString());
    }
}