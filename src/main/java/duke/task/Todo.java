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

    @Override
    public String toString() {
        return String.format("[T]%s %s", isDone ? "[X]" : "[ ]", description);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "todo");
        obj.put("description", description);
        obj.put("isDone", isDone);
        return obj;
    }
}
