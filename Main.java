import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        manager.registerUser("manager1", "MANAGER");
        manager.registerUser("manager2", "MANAGER");
        manager.registerUser("ali", "MEMBER");
        manager.registerUser("reza", "MEMBER");
        manager.registerUser("sara", "MEMBER");

        manager.login("manager1");

        manager.addTask(new SimpleTask(1, "Java Homework", LocalDate.now().plusDays(3)));
        manager.addTask(new UrgentTask(2, "Project", LocalDate.now().minusDays(2), 5));
        manager.addTask(new UrgentTask(3, "Exam", LocalDate.now().plusDays(5), 4));
        manager.addTask(new SimpleTask(4, "Read Book", LocalDate.now().plusDays(1)));
        manager.addTask(new UrgentTask(5, "Meeting", LocalDate.now().minusDays(1), 4));
        manager.addTask(new SimpleTask(6, "Practice", LocalDate.now().plusDays(4)));

        manager.assignTaskToUser(1, "ali");
        manager.assignTaskToUser(2, "reza");
        manager.assignTaskToUser(3, "sara");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nTASK MANAGER MENU");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Add Task");
            System.out.println("5. Assign Task");
            System.out.println("6. Mark Task Completed");
            System.out.println("7. Show All Tasks");
            System.out.println("8. Run Reports");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    manager.registerUser(sc.next(), "MEMBER");
                    break;
                case 2:
                    System.out.print("Username: ");
                    manager.login(sc.next());
                    break;
                case 3:
                    manager.logout();
                    break;
                case 4:
                    manager.addTask(new SimpleTask(10, "New Task", LocalDate.now().plusDays(2)));
                    break;
                case 5:
                    System.out.print("Task ID: ");
                    int taskId = sc.nextInt();
                    System.out.print("Username: ");
                    manager.assignTaskToUser(taskId, sc.next());
                    break;
                case 6:
                    System.out.print("Task ID: ");
                    manager.markTaskCompleted(sc.nextInt());
                    break;
                case 7:
                    manager.displayAllTasks();
                    break;
                case 8:
                    manager.sortTasksByPriority();
                    manager.getHighPriorityTasks();
                    manager.countTasksByType();
                    manager.averagePriority();
                    manager.getOverdueTasks();
                    manager.findBusiestUser();
                    manager.printTaskSummary();
                    break;
                case 9:
                    return;
            }
        }
    }
}