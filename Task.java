import java.time.LocalDate;

public abstract class Task implements Prioritizable {

    protected int id;
    protected String title;
    protected String assignee;       // نام کاربر مسئول
    protected LocalDate deadline;    // مهلت انجام
    protected boolean completed;     // آیا انجام شده؟

    public Task(int id, String title, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.completed = false;
        this.assignee = null;
    }

    public abstract String getType();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + getType()
                + " | Priority: " + getPriority() + " (" + getPriorityLabel() + ")"
                + " | User: " + (assignee != null ? assignee : "Unassigned")
                + " | Deadline: " + deadline
                + " | Completed: " + completed;
    }
}