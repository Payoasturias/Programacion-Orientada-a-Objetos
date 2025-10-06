// Programación Orientada a Objetos
// Universidad del Valle de Guatemala
// Sección 40
// Ejercicio 5 - Polimorfismo vía herencia
// Paolo Asturias
// Carné: 25722


// Main.java
// Acts as the entry point of the program.
// It creates a ProcessManager instance (Controller), registers several Process objects (Model), and uses the ProcessView (View) indirectly through the manager.

public class Main
{
    public static void main(String[] args)
    {
        // Create the process manager (Controller)
        ProcessManager manager = new ProcessManager();

        // Pre-register some example processes
        manager.addProcess(new CPUProcess(1, "Compiler"));
        manager.addProcess(new IOProcess(2, "DiskReader"));
        manager.addProcess(new DaemonProcess(3, "Logger"));

        // Start the interactive menu handled inside the manager (it encapsulates the view)
        manager.startInteractive();
    }
}
