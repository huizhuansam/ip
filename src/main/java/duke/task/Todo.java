package duke.task;

import org.json.simple.JSONObject;

/** Represents a Todo object. */
public class Todo extends Task {
    /**
     * Todo constructor.
     *
     * @param description The description of the Todo.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Todo constructor.
     *
     * @param description The description of the Todo.
     * @param isDone The done status of the Todo.
     */
    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Factory Todo method.
     *
     * @param description The description
     * @return A new Todo object
     */
    public static Todo of(String description) {
        return new Todo(description);
    }

    /**
     * Factory Todo method.
     *
     * @param description The description.
     * @param isDone The done status of the Todo.
     * @return a new Todo object
     */
    public static Todo of(String description, boolean isDone) {
        return new Todo(description, isDone);
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        assert description != null;
        return String.format("[T]%s %s", isDone ? "[X]" : "[ ]", description);
    }

    /**
     * Returns a JSON representation of the Todo object.
     *
     * @return A JSON representation of the Todo object.
     */
    @Override
    @SuppressWarnings("unchecked") // Type warning due to JSON simple library. Type safety guaranteed. Just use it.
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "todo");
        obj.put("description", description);
        obj.put("isDone", isDone);
        return obj;
    }

    /**
     * Comparator method. DO NOT USE (yet)! Implemented to bypass abstract superclass implementing Comparable interface.
     *
     * @param otherTask The other task to be compared to.
     * @return 0.
     */
    @Override
    public int compareTo(Task otherTask) {
        return 0;
    }
}
