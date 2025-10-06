// CPUProcess.java
// Represents a process that consumes high CPU usage by doing many calculations.
// Example in real life: code compilation, rendering 3D graphics, or data encryption.

public class CPUProcess extends Process
{
    public CPUProcess(int pid, String name)
    {
        super(pid, name);
    }

    @Override
    public String execute()
    {
        // Simulate CPU-intensive calculations:
        int result = 0;
        for (int i = 1; i <= 1000; i++)
        {
            result += (i * i);
        }
        return "CPU result=" + result;
    }
}