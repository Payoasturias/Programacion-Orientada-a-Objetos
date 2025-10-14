// Programación Orientada a Objetos
// Universidad del Valle de Guatemala
// Sección 40
// Lab 3 - Herencia
// Paolo Asturias
// Carné: 25722

// =============================================
// Main.java
// Entry point of the Hospital Management System.
// Instantiates HospitalManager and HospitalView, then starts the console UI.
// =============================================

public class Main
{
    public static void main(String[] args)
    {
        HospitalManager manager = new HospitalManager();
        HospitalView view = new HospitalView(manager);

        // Start the interactive console menu (blocks until user exits)
        view.startMenu();

        // Graceful shutdown message
        view.showMessage("Application terminated. Goodbye...");
    }
}