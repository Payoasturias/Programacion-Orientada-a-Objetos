import java.util.ArrayList;

// ProcessManager.java
// Acts as the Controller in the MVC pattern.
// Manages a collection of Process objects and coordinates their execution.

public class ProcessManager
{
    private ArrayList<Process> processes;  // Stores all processes
    private ProcessView view;              // Handles user interaction and display

    // Constructor initializes the process list and creates a ProcessView instance
    public ProcessManager()
    {
        processes = new ArrayList<>();
        view = new ProcessView();  // View is instantiated here
    }

    // Adds a new process to the list.
    // Checks for duplicate PIDs before adding.
    public void addProcess(Process p)
    {
        if (p == null)
        {
            return;
        }

        // Prevent duplicate process IDs
        for (Process existing : processes)
        {
            if (existing.getPid() == p.getPid())
            {
                view.showProcessExecution(existing); // Notify duplicate
                return;
            }
        }

        processes.add(p);
    }

    // Executes all registered processes using polymorphism.
    // Each process runs its own execute() method.
    // After execution, the result is displayed through the view.

    public void runAll()
    {
        if (processes.isEmpty())
        {
            view.showMessage("[INFO] No processes to run.");

            return;
        }

        for (Process p : processes)
        {
            String result = p.execute();  // Polymorphic call (runtime binding) returns a result
            view.showProcessExecution(p);
            if (result != null)
            {
                view.showMessage("[RESULT] " + result);
            }
        }
    }

    // Displays all registered processes using the view.
    public void showAll()
    {
        if (processes.isEmpty())
        {
            view.showMessage("(No processes registered)");
            return;
        }

        for (Process p : processes)
        {
            view.showProcessInfo(p);
        }
    }

    // Starts an interactive menu loop handled entirely by the ProcessManager.
    // This keeps the view encapsulated inside the controller so callers (Main)
    // don't need to create or know about ProcessView.
    public void startInteractive()
    {
        boolean running = true;
        while (running)
        {
            view.showMenu();
            String choice = view.getUserInput();

            switch (choice)
            {
                case "1":
                    runAll();
                    break;
                case "2":
                    showAll();
                    break;
                case "0":
                    running = false;
                    view.showMessage("Exiting...");
                    break;
                default:
                    view.showMessage("Invalid option.");
            }
        }
    }
}
