import java.time.LocalDate;

public class UrgentTask extends Task {
    private int customPriority;

    public UrgentTask(int id, String title, LocalDate deadline, int priority) {
        super(id, title, deadline);
        if (priority < 4) {
            this.customPriority = 4;
        } else if (priority > 5) {
            this.customPriority = 5;
        } else {
            this.customPriority = priority;
        }
    }

    @Override
    public String getType() {
        return "Urgent";
    }

    @Override
    public int getPriority() {
        return customPriority;
    }

    @Override
    public String getPriorityLabel() {
        if (customPriority == 5) {
            return "Critical";
        }
        return "High";
    }
}