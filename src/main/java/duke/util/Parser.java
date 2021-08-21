package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.enums.Commands;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.UnknownCommandException;

/** Utility class that handles parsing of user input to program command. */
public class Parser {
    /**
     * Returns a Commands enum type from the user's full command input by regex splitting the command string and
     * returning the first word.
     *
     * @param fullCommand The full command input from the user.
     * @return Commands enum type.
     */
    private static Commands commandParser(String fullCommand) {
        return Commands.valueOfLabel(fullCommand.trim().split(" ")[0]);
    }

    /**
     * Returns a string containing the content of the full command input by removing the command keyword and returning
     * the remainder of the string using regex.
     *
     * @param fullCommand The full command input from the user.
     * @return The content of the command as string.
     */
    private static String contentParser(String fullCommand) {
        String[] token = fullCommand.trim().split(" ", 2);
        if (token.length < 2) {
            return "";
        }
        return token[1];
    }

    /**
     * Checks for empty user input.
     *
     * @param fullCommand The full command input from the user.
     * @throws EmptyCommandException If the command is empty or consists of only whitespace.
     */
    private static void blankCommandChecker(String fullCommand) throws EmptyCommandException {
        if (fullCommand.trim().length() < 1) {
            throw new EmptyCommandException();
        }
    }

    /**
     * Parses the user's full input to Command object.
     *
     * @param fullCommand The full command input from the user.
     * @return A Command object.
     * @throws DukeException If an exception is found.
     */
    public static Command parse(String fullCommand) throws DukeException {
        blankCommandChecker(fullCommand);
        Command command;
        switch (commandParser(fullCommand)) {
        case EXIT:
            command = ExitCommand.of(contentParser(fullCommand));
            break;
        case LIST:
            command = ListCommand.of(contentParser(fullCommand));
            break;
        case ADD:
            command = AddCommand.of(contentParser(fullCommand));
            break;
        case DONE:
            command = DoneCommand.of(contentParser(fullCommand));
            break;
        case DELETE:
            command = DeleteCommand.of(contentParser(fullCommand));
            break;
        case FIND:
            command = FindCommand.of(contentParser(fullCommand));
            break;
        default:
            throw new UnknownCommandException(fullCommand);
        }
        return command;
    }
}
