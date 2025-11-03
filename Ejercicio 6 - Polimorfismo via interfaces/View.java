// ==============================
// Class: View
// Handles all user interaction
// ==============================

import java.util.ArrayList;
import java.util.Scanner;

public class View
{
    private Scanner sc = new Scanner(System.in);

    public void showMenu()
    {
        System.out.println("=== Smart Farming Monitoring System ===");
        System.out.println("1. List all devices");
        System.out.println("2. Search device by ID");
        System.out.println("3. Search device by Name");
        System.out.println("4. Sort devices by Power Consumption");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public void displayDevice(Device device)
    {
        if (device != null)
        {
            System.out.println(device.toString());
        }

        else
        {
            System.out.println("Device not found.");
        }
    }

    public void showList(ArrayList<Device> devices)
    {
        for (Device d : devices)
        {
            System.out.println(d.toString());
        }
    }

    public String readInput()
    {
        return sc.nextLine();
    }
}
