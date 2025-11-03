// =============================================
// ABSTRACT CLASS: Device
// Base class for all devices in the cooperative system.
// Implements Comparable to allow sorting by power consumption.
// =============================================

public abstract class Device implements Comparable<Device>
{
    private String id;
    private String name;
    private double powerConsumption;

    public Device(String id, String name, double powerConsumption)
    {
        this.id = id;
        this.name = name;
        this.powerConsumption = powerConsumption;
    }

    // Essential getters/setters
    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getPowerConsumption()
    {
        return powerConsumption;
    }

    // Comparable: order by powerConsumption ascending
    @Override
    public int compareTo(Device other)
    {
        return Double.compare(this.powerConsumption, other.powerConsumption);
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " | Name: " + name + " | Power: " + powerConsumption + "W";
    }
}
