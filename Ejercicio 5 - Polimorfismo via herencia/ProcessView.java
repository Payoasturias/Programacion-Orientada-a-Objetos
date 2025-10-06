import java.util.Scanner;

// ProcessView.java
// Handles all user interaction.
// Only this class prints to console or reads user input.

public class ProcessView
{
    private Scanner scanner;

    // Constructor
    public ProcessView()
    {
        scanner = new Scanner(System.in);
    }

    // Displays information about a single process.
    public void showProcessInfo(Process p)
    {
        System.out.println(p.toString());
    }

    // Displays a message after a process has been executed.
    public void showProcessExecution(Process p)
    {
        System.out.println("[EXECUTED] " + p.toString());
    }

    // Shows the main menu to the user.
    public void showMenu()
    {
        System.out.println("=== Process Manager Menu ===");
        System.out.println("1. Run all processes");
        System.out.println("2. Show all processes");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public String getUserInput()
    {
        return scanner.nextLine();
    }

    // Convenience method to display a plain message
    public void showMessage(String message)
    {
        System.out.println(message);
    }
}
