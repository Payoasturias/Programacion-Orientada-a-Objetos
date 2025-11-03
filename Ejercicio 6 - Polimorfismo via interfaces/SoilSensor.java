// =============================================
// CLASS: SoilSensor.java
// Measures soil humidity and stores the last measured value
// Implements Measurable and Registrable
// =============================================

import java.util.Random;

public class SoilSensor extends Device implements Measurable, Registrable
{
    private double lastValue;

    public SoilSensor(String id, String name, double powerConsumption)
    {
        super(id, name, powerConsumption);
    }

    @Override
    public double measure()
    {
        double value = Math.random() * 100; // Simulated humidity
        register(value);
        return value;
    }

    @Override
    public void register(double value)
    {
        this.lastValue = value;
    }

    @Override
    public double getLastValue()
    {
        return lastValue;
    }

    @Override
    public String toString()
    {
        return super.toString() + " | Type: SoilSensor | Moisture : " + String.format("%.2f", lastValue) + "%";
    }
}