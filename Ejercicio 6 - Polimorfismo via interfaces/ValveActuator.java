// =============================================
// CLASS: ValveActuator.java
// Controls a valve (open/close). Implements Actionable.
// =============================================

import java.util.Random;

public class ValveActuator extends Device implements Actionable, Registrable
{
    private boolean isOpen; // Internal state to simulate valve status
    private String lastActionResult;

    // Constructor
    public ValveActuator(String id, String name, double powerConsumption)
    {
        super(id, name, powerConsumption);
        this.isOpen = false; // default state: closed
        this.lastActionResult = "Idle";
    }

    /// Performs an action such as "open" or "close".
    // Updates internal state and stores a description of what happened.
    @Override
    public void performAction(String action)
    {
        // Simulates random action if called with null
        if (action == null || action.isEmpty())
        {
            action = Math.random() > 0.5 ? "open" : "close";
        }

        if (action.equalsIgnoreCase("open"))
        {
            isOpen = true;
            register(1.0);
        }
        
        else if (action.equalsIgnoreCase("close"))
        {
            isOpen = false;
            register(0.0);
        }

        lastActionResult = action;
    }

    @Override
    public void register(double value)
    {
        // value: 1 = open, 0 = close
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public double getLastValue()
    {
        return isOpen ? 1.0 : 0.0;
    }

    public String getLastActionResult()
    {
        return lastActionResult;
    }

    @Override
    public String toString()
    {
        return super.toString() + " | Type: ValveActuator | State: " + (isOpen ? "OPEN" : "CLOSED") + " | Last Action: " + lastActionResult;
    }
}