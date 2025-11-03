// Programación Orientada a Objetos
// Universidad del Valle de Guatemala
// Sección 40
// Ejercicio 6 - Polimorfismo vía interfaces
// Paolo Asturias
// Carné: 25722

// ==============================
// Class: Main
// Entry point of the application
// ==============================

public class Main
{
    public static void main(String[] args)
    {
        DeviceManager manager = new DeviceManager();
        View view = new View();
        manager.initDevices();

        boolean running = true;
        while (running)
        {
            view.showMenu();
            String choice = view.readInput();

            switch (choice)
            {
                case "1":
                    view.showList(manager.listAllDevices());
                    break;
                case "2":
                    System.out.print("Enter device ID: ");
                    try
                    {
                        String id = view.readInput().trim();
                        view.displayDevice(manager.findDeviceById(id));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid ID. Please enter a numeric value.");
                    }
                    break;
                case "3":
                    System.out.print("Enter device Name: ");
                    view.displayDevice(manager.findDeviceByName(view.readInput()));
                    break;
                case "4":
                    manager.sortByPowerConsumption();
                    System.out.println("Devices sorted by power consumption:");
                    view.showList(manager.listAllDevices());
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}