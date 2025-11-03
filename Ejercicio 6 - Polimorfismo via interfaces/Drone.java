// =============================================
// CLASS: Drone.java
// Generic drone capable of performing actions (e.g., irrigate, scan).
// Implements Actionable.
// =============================================

public class Drone extends Device implements Actionable
{
    private String lastAction;

    public Drone(String id, String name, double powerConsumption)
    {
        super(id, name, powerConsumption);
        this.lastAction = "Idle";
    }

    // Execute drone actions. Supported actions: "takeoff", "land", "spray".
    @Override
    public void performAction(String action)
    {
        if (action == null || action.isEmpty())
        {
            action = Math.random() > 0.5 ? "spray" : "capture";
        }
        lastAction = action;
    }

    public String getLastAction()
    {
        return lastAction;
    }

    @Override
    public String toString()
    {
        return super.toString() + " | Type: Drone | Last Action: " + lastAction;
    }
}