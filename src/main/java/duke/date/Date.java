package duke.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/** A Date wrapper class. */
public class Date {
    /** The LocalDate. */
    private final LocalDate date;

    /**
     * The Date constructor.
     *
     * @param date The LocalDate.
     */
    Date(LocalDate date) {
        this.date = date;
    }

    /**
     * Date factory method.
     *
     * @param dateString The date string in yyyy-MM-dd format.
     * @return A Date object.
     * @throws InvalidDateException If the given dateString is not in yyyy-MM-dd format.
     */
    public static Date of(String dateString) throws InvalidDateException {
        try {
            return new Date(LocalDate.parse(dateString));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Parses the Date into a JSON string.
     *
     * @return A JSON string representation of the Date.
     */
    public String toJsonString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * @return A string representation of the Date object.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Checks for date equality.
     *
     * @param other The object to be compared with.
     * @return True if the other object is an instance of Date and contains the same string representation.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Date) {
            Date another = (Date) other;
            return another.toString().equals(this.toString());
        }
        return false;
    }
}
