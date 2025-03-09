import java.util.Scanner;
import java.util.Stack;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class DailyTaskManager {

    static String reset = "\u001b[0m", red = "\u001b[31m", yellow = "\u001b[33m", green = "\u001b[32m";

    String[] tasksArray = {"Play with frogs", "Snooze", "PLay with leopard gecko", "chilling", "play valorant"};
    Stack<Integer> taskArrayStatus = new Stack<>();
    LinkedList<String> taskList = new LinkedList<>();
    Stack<Integer> taskListStatus = new Stack<>();

    static Scanner scanner = new Scanner(System.in);

    public static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(red + "Yo, type a legit number: " + reset);
                scanner.next();
            }
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Oops! Can't clear the screen.");
        }
    }

    public static void displayTasks(String[] tasksArray, Stack<Integer> taskArrayStatus) {
        for (int i = 0; i < tasksArray.length; i++) {
            System.out.print((i + 1) + ". " + tasksArray[i]);
            if (taskArrayStatus.contains(i)) {
                System.out.println(green + " (Done and Dusted)" + reset);
            } else {
                System.out.println(red + " (Still Pending...)" + reset);
            }
        }
    }

    public static void updateTask(String[] tasksArray, Stack<Integer> taskArrayStatus) {
        while (true) {
            System.out.print("Which task needs a makeover? (0 to bail out): ");
            scanner.nextLine();
            int index = getIntInput();

            if (index == 0) {
                clearScreen();
                break;
            } else if (index > tasksArray.length || index < 0) {
                System.out.println(red + "Task not found, buddy." + reset);
            } else {
                index--;
                System.out.print("Spill the new task name: ");
                scanner.nextLine();
                String newTask = scanner.nextLine();
                if (taskArrayStatus.contains(index)) {
                    taskArrayStatus.remove(taskArrayStatus.indexOf(index));
                }
                clearScreen();
                System.out.println(yellow + "Yo! Task " + tasksArray[index] + " is now " + newTask + "!" + reset);
                tasksArray[index] = newTask;
                break;
            }
        }
    }

    public static void markTaskAsDone(String[] tasksArray, Stack<Integer> taskArrayStatus) {
        while (true) {
            System.out.print("Which task you wanna check off? (0 to exit): ");
            scanner.nextLine();
            int index = getIntInput();

            if (index == 0) {
                clearScreen();
                break;
            } else if (index > tasksArray.length || index < 0) {
                System.out.println(red + "Task not found, dude." + reset);
            } else {
                index--;
                if (taskArrayStatus.contains(index)) {
                    System.out.println(yellow + "That one's already done!" + reset);
                } else {
                    taskArrayStatus.push(index);
                    clearScreen();
                    System.out.println(green + "Boom! Task " + tasksArray[index] + " is complete!" + reset);
                }
            }
        }
    }

    public void menuArray() {
        while (true) {
            System.out.println("""
=============================
What's the move?
1. Check the task list
2. Revamp a task
3. Mark task as done
4. Back to base
=============================
            """);
            System.out.print("Drop your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    clearScreen();
                    displayTasks(tasksArray, taskArrayStatus);
                    break;
                case 2:
                    updateTask(tasksArray, taskArrayStatus);
                    break;
                case 3:
                    markTaskAsDone(tasksArray, taskArrayStatus);
                    break;
                case 4:
                    clearScreen();
                    return;
                default:
                    clearScreen();
                    System.out.println(red + "Nah, that's not an option!" + reset);
            }
        }
    }

    public static void main(String[] args) {
        DailyTaskManager manager = new DailyTaskManager();
        clearScreen();
        while (true) {
            System.out.print("""
=============================
Pick your menu:
(0 to bounce)
1. Task Hub (Array Style)
=============================
Your move:  """);
            int choice = getIntInput();
            if (choice == 1) {
                clearScreen();
                manager.menuArray();
            } else if (choice == 0) {
                scanner.close();
                System.out.print(green + "Catch ya later!" + reset);
                break;
            } else {
                clearScreen();
                System.out.println(red + "Nope, try again!" + reset);
            }
        }
    }
}
