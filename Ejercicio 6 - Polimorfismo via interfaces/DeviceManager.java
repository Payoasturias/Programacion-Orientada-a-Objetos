// =============================================
// DeviceManager.java
// It contains the device catalog (a single polymorphic list)
// and coordinates system operations.
// =============================================

import java.util.ArrayList;
import java.util.Collections;

public class DeviceManager
{
    private ArrayList<Device> devices;

    public DeviceManager()
    {
        devices = new ArrayList<>();
    }

    // Initialize the devices
    public void initDevices()
    {
        devices.add(new SoilSensor("S01", "Soil Sensor A", 5.5));
        devices.add(new SoilSensor("S02", "Soil Sensor B", 5.0));
        devices.add(new WeatherStation("W01", "Weather Station X", 10.2));
        devices.add(new WeatherStation("W02", "Weather Station Y", 11.0));
        devices.add(new ValveActuator("V01", "Main Valve", 8.5));
        devices.add(new ValveActuator("V02", "Backup Valve", 7.5));
        devices.add(new Drone("D01", "Drone Alpha", 15.5));
        devices.add(new Drone("D02", "Drone Beta", 18.0));
        devices.add(new SoilSensor("S03", "Soil Sensor C", 6.3));
        devices.add(new WeatherStation("W03", "Weather Station Z", 8.7));

        // Initialize dynamic states
        for (Device d: devices)
        {
            if (d instanceof Measurable m)
            {
                m.measure();
            }

            if (d instanceof Actionable a)
            {
                a.performAction(null);
            }
        }
    }

    // Return all devices
    public ArrayList<Device> listAllDevices()
    {
        return devices;
    }

    // Search by ID
    public Device findDeviceById(String id)
    {
        for (Device d : devices)
        {
            if (d.getId().equalsIgnoreCase(id))
            {
                return d;
            }
        }
        return null;
    }

    // Search by Name
    public Device findDeviceByName(String name)
    {
        for (Device d : devices)
        {
            if (d.getName().equalsIgnoreCase(name))
            {
                return d;
            }
        }
        return null;
    }

    // Sort by electricity consumption
    public void sortByPowerConsumption()
    {
        Collections.sort(devices);
    }
}