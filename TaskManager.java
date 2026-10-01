import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private Map<Integer, Task> tasks;
    private Map<String, User> users;
    private User currentUser;
    private int nextTaskId;

    public TaskManager() {
        tasks = new HashMap<>();
        users = new HashMap<>();
        currentUser = null;
        nextTaskId = 7;
    }

    public void registerUser(String username, String role) {
        if (users.containsKey(username)) {
            return;
        }
        if (!role.equals("MANAGER") && !role.equals("MEMBER")) {
            return;
        }
        users.put(username, new User(username, role));
    }

    public void login(String username) {
        if (!users.containsKey(username)) {
            return;
        }
        currentUser = users.get(username);
    }

    public void logout() {
        if (currentUser == null) {
            return;
        }
        currentUser = null;
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void assignTaskToUser(int taskId, String username) {
        if (!tasks.containsKey(taskId)) {
            return;
        }
        if (!users.containsKey(username)) {
            return;
        }

        Task task = tasks.get(taskId);
        User user = users.get(username);

        if (task.getAssignee() != null) {
            return;
        }

        task.setAssignee(username);
        user.addTask(task);
    }

    public void markTaskCompleted(int taskId) {
        if (!tasks.containsKey(taskId)) {
            return;
        }
        Task task = tasks.get(taskId);
        if (task.isCompleted()) {
            return;
        }
        task.markCompleted();
    }

    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\nAll Tasks:");
        tasks.values().forEach(System.out::println);
        System.out.println();
    }

    public void sortTasksByPriority() {
        System.out.println("\nTasks Sorted By Priority:");
        tasks.values().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()))
                .forEach(System.out::println);
        System.out.println();
    }

    public void getHighPriorityTasks() {
        System.out.println("\nHigh Priority Tasks (Priority >= 4):");
        List<Task> high = tasks.values().stream()
                .filter(t -> t.getPriority() >= 4)
                .collect(Collectors.toList());
        if (high.isEmpty()) {
            System.out.println("None");
        } else {
            high.forEach(System.out::println);
        }
        System.out.println();
    }

    public void countTasksByType() {
        long simpleCount = tasks.values().stream().filter(t -> t.getType().equals("Simple")).count();
        long urgentCount = tasks.values().stream().filter(t -> t.getType().equals("Urgent")).count();
        System.out.println("\nTask Count By Type:");
        System.out.println("Simple: " + simpleCount);
        System.out.println("Urgent: " + urgentCount);
        System.out.println();
    }

    public void averagePriority() {
        double avg = tasks.values().stream()
                .mapToInt(Task::getPriority)
                .average()
                .orElse(0);
        System.out.println("\nAverage Priority:");
        System.out.printf("%.2f / 5.00\n", avg);
        System.out.println();
    }

    public void getOverdueTasks() {
        System.out.println("\nOverdue Tasks:");
        LocalDate today = LocalDate.now();
        List<Task> overdue = tasks.values().stream()
                .filter(t -> !t.isCompleted() && t.getDeadline().isBefore(today))
                .collect(Collectors.toList());

        if (overdue.isEmpty()) {
            System.out.println("None");
        } else {
            overdue.forEach(System.out::println);
        }
        System.out.println();
    }

    public void findBusiestUser() {
        System.out.println("\nBusiest User:");
        Map<String, Long> taskCountByUser = tasks.values().stream()
                .filter(t -> t.getAssignee() != null)
                .collect(Collectors.groupingBy(Task::getAssignee, Collectors.counting()));

        if (taskCountByUser.isEmpty()) {
            System.out.println("No tasks assigned");
            return;
        }

        String busiest = taskCountByUser.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        System.out.println(busiest + " (" + taskCountByUser.get(busiest) + " tasks)");
        System.out.println();
    }

    public void printTaskSummary() {
        long completed = tasks.values().stream().filter(Task::isCompleted).count();
        long pending = tasks.size() - completed;
        System.out.println("\nTask Summary:");
        System.out.println("Total: " + tasks.size());
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);
        System.out.println();
    }
}