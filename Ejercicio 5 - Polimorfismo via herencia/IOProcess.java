// IOProcess.java
// Represents a process that interacts with external devices (keyboard, disk, or network).
// These processes usually wait for data to arrive or for an operation to complete.
// Example in real life: reading from a disk, waiting for user input, or fetching a web page.

public class IOProcess extends Process
{

    public IOProcess(int pid, String name)
    {
        super(pid, name);
    }

    @Override
    public String execute()
    {
        // Simulates an I/O process: The use of (i % 3) creates a repetitive pattern: 0, 1, 2, 0, 1, 2...
        // This represents waiting cycles typical for I/O operations.
        int counter = 0;
        for (int i = 0; i < 50000; i++)
        {
            counter += (i % 3);
        }
        return "IO counter=" + counter;
    }  
}
