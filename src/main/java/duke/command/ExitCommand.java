package duke.command;

import duke.exception.BadInputFormatException;
import duke.util.Storage;
import duke.util.TaskList;

/** Represents the "exit" command. */
public class ExitCommand extends Command {
    /**
     * ExitCommand factory method.
     *
     * @param content The user's input content.
     * @return An ExitCommand object.
     * @throws BadInputFormatException If the user's input contains more than a singular "bye" keyword.
     */
    public static ExitCommand of(String content) throws BadInputFormatException {
        if (content.trim().length() > 1) { // Guard clause
            throw new BadInputFormatException();
        }
        return new ExitCommand();
    }

    /**
     * Cleans up the UI and writes to disk.
     *
     * @param tasks The list of tasks in the program.
     * @param storage The storage utility.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.write(tasks);
        return formatOutput("Bye. See ya l8er allig8er!", "*shutting down......*");
    }

    /**
     * Tests if a command is exit.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
