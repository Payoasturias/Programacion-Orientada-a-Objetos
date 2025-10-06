// DaemonProcess.java
// Represents a background process that runs continuously to provide services.
// Example: logging, system monitoring, automatic backups.

public class DaemonProcess extends Process
{
    // Constructor
    public DaemonProcess(int pid, String name)
    {
        super(pid, name);
    }

    @Override
    public String execute()
    {
        // Simulate background monitoring
        int checks = 0;
        for (int i = 0; i < 5; i++)
        {
            if (i % 2 == 0)
            {
                checks += i; // even iteration: simulate positive event
            }
            
            else
            {
                checks -= i; // odd iteration: simulate negative event
            }
        }
        return "Daemon checks=" + checks;
    }
}