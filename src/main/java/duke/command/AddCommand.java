package duke.command;

import duke.enums.Tasks;
import duke.exception.BadInputFormatException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateException;
import duke.exception.UnknownTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;

/** Represents an "add" command */
public class AddCommand extends Command {
    /** The Task object. */
    private final Task task;

    /**
     * AddCommand constructor.
     *
     * @param task The Task object.
     */
    private AddCommand(Task task) {
        this.task = task;
    }

    /**
     * AddCommand factory method.
     *
     * @param content The user's input content.
     * @return An AddCommand.
     * @throws BadInputFormatException If the input is badly formatted.
     * @throws UnknownTaskTypeException If the task type is unknown.
     * @throws EmptyDescriptionException If the description is empty.
     * @throws InvalidDateException If the date is not in yyyy-MM-dd format.
     */
    public static AddCommand of(String content)
            throws BadInputFormatException,
            UnknownTaskTypeException,
            EmptyDescriptionException,
            InvalidDateException {
        AddCommand command;
        switch (parseToTaskType(content)) {
        case TODO:
            command = new AddCommand(Todo.of(parseToTaskDescription(content)));
            break;
        case DEADLINE:
            command = new AddCommand(Deadline.of(parseToTaskDescription(content)));
            break;
        case EVENT:
            command = new AddCommand(Event.of(parseToTaskDescription(content)));
            break;
        default:
            throw new UnknownTaskTypeException(content);
        }
        return command;
    }

    /**
     * Adds the Task into the TaskList.
     *
     * @param tasks The list of tasks in the program.
     * @param storage The storage utility.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        return formatOutput("Hehe buoi, I've added this task:",
                task.toString(),
                String.format("Now you have %d %s in the list.", tasks.getSize(), tasks.getSize() == 1 ? "task" : "tasks"));
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Helper method that parses the user input and returns a Tasks enum according to the first word of the input split
     * by regex.
     *
     * @param content The user's input content.
     * @return A Tasks enum.
     */
    private static Tasks parseToTaskType(String content) {
        return Tasks.valueOfLabel(content.trim().split(" ")[0]);
    }

    /**
     * Helper method that parses the user input and returns a string that contains the description of the Task split by
     * regex.
     *
     * @param content The user's input content.
     * @return The description of the Task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    private static String parseToTaskDescription(String content) throws EmptyDescriptionException {
        try {
            return content.trim().split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException();
        }
    }
}
