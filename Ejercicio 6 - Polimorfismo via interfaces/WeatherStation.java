// =============================================
// CLASS: WeatherStation.kava
// Measures and stores the current temperature
// Implements Measurable and Registrable
// =============================================

public class WeatherStation extends Device implements Measurable, Registrable 
{
    private double lastTemperature;

    public WeatherStation(String id, String name, double powerConsumption)
    {
        super(id, name, powerConsumption);
    }

    @Override
    public double measure()
    {
        double temp = 10 + Math.random() * 25; // Simulated temperature
        register(temp);
        return temp;
    }

    @Override
    public void register(double value)
    {
        this.lastTemperature = value;
    }

    @Override
    public double getLastValue()
    {
        return lastTemperature;
    }

    @Override
    public String toString()
    {
        return super.toString() + " | Type: WeatherStation | Last Temp: " + String.format("%.2f", lastTemperature) + "°C";
    }
}