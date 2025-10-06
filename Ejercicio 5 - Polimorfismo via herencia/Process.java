// File: Process.java
// Description: Abstract base class for processes.

public abstract class Process
{
    private int pid;
    private String name;

    // Constructor
    public Process(int pid, String name)
    {
        this.pid = pid;
        this.name = name;
    }

    // Methods

    // Abstract method to be implemented by each process type
    public abstract String execute();

    // toString showing only pid and name
    @Override
    public String toString()
    {
        return "Process(pid=" + pid + ", name=" + name + ")";
    }

    // Getters and Setters

    public int getPid()
    {
        return pid;
    }

    public void setPid(int pid)
    {
        this.pid = pid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}