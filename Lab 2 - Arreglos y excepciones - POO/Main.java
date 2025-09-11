import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Vista vista = new Vista();

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el nombre del Jugador 1: ");
        Jugador j1 = new Jugador(sc.nextLine());

        System.out.print("Ingrese el nombre del Jugador 2: ");
        Jugador j2 = new Jugador(sc.nextLine());

        boolean jugar = true;

        while(jugar)
        {
            try
            {
                int dimension = vista.leerDimension();
                JuegoMemoria juego = new JuegoMemoria(j1, j2, dimension);
                juego.iniciarPartida();

                jugar = vista.preguntarNuevaPartida();
            }

            catch (IllegalArgumentException e)
            {
                vista.mostrarMensaje("⚠️ Dimensión inválida: " + e.getMessage());
            }
        }

        vista.mostrarMensaje("\nGracias por jugar 🎉");
    }
}