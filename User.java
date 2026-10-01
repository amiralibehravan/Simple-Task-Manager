import java.util.ArrayList;
import java.util.List;

public class User {
    private String UserName;
    private String role;
    private List<Task> assignedTasks ;

    public User(String userName, String role) {
        UserName = userName;
        this.role = role;
        assignedTasks = new ArrayList<>();
    }

    public String getUserName() {
        return UserName;
    }

    public String getRole() {
        return role;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void addTask(Task task){
        assignedTasks.add(task);

    }

    public void viewProfile(){

        System.out.println(UserName+" - "+role);

        assignedTasks.forEach(System.out::println);
    }

}
