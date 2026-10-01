import java.time.LocalDate;

public class SimpleTask extends Task {

    public SimpleTask(int id, String title, LocalDate deadline) {
        super(id, title, deadline);
    }

    @Override
    public String getType() {
        return "Simple";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getPriorityLabel() {
        return "Normal";
    }
}
