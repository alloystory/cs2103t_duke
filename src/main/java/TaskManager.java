import java.util.List;
import java.util.ArrayList;
import exceptions.WrongUsageException;

public class TaskManager{
    private List<Task> tasks;

    public TaskManager(int maxTasks) {
        this.tasks = new ArrayList<>(maxTasks);
    }

    public String addTodoTask(String args) throws WrongUsageException {
        if (args.equals("")) throw new WrongUsageException("Usage: todo <description>");
        return this.add(new Task(args));
    }

    public String addDeadlineTask(String args) throws WrongUsageException {
        String[] splitArgs = args.split("/by");
        if (splitArgs.length != 2) throw new WrongUsageException("Usage: deadline <description> /by <deadline>");

        return this.add(new DeadlineTask(splitArgs[0].trim(), splitArgs[1].trim()));
    }

    public String addEventTask(String args) throws WrongUsageException {
        String[] splitArgs = args.split("/at");
        if (splitArgs.length != 2) throw new WrongUsageException("Usage: event <description> /at <when>");

        return this.add(new EventTask(splitArgs[0].trim(), splitArgs[1].trim()));
    }

    public String setTaskDone(String args) throws WrongUsageException {
        try {
            int index = Integer.parseInt(args);
            Task task = this.tasks.get(index - 1);
            task.setDone();

            return String.format(
                "Nice! I've marked this task as done: \n%s", task
            );
        } catch (NumberFormatException e) {
            throw new WrongUsageException("Usage: done <task_index>");
        } catch (IndexOutOfBoundsException e) {
            throw new WrongUsageException("Invalid task index. Please refer to the 'list' command for available indices.");
        }
    }

    public String deleteTask(String args) throws WrongUsageException {
        try {
            int index = Integer.parseInt(args);
            Task task = this.tasks.remove(index - 1);

            return String.format(
                "Noted. I've removed this task: \n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", task, this.tasks.size()
            );
        } catch (NumberFormatException e) {
            throw new WrongUsageException("Usage: delete <task_index>");
        } catch (IndexOutOfBoundsException e) {
            throw new WrongUsageException("Invalid task index. Please refer to the 'list' command for available indices.");
        }
    }

    // -----------------------------------------------------------------------------------------
    // Helper Methods
    private String add(Task task) {
        this.tasks.add(task);
        return String.format(
            "Got it. I've added this task:\n" +
            "  %s\n" +
            "Now you have %d tasks in the list.\n", task, this.tasks.size()
        );
    }

    @Override
    public String toString() {
        if (this.tasks.size() == 0) return "You have no tasks";

        StringBuilder sb = new StringBuilder("These are your tasks: \n");
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        return sb.toString();
    }
}