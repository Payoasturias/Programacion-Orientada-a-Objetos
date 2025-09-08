// Clase Vista: maneja la interacción con el usuario

import java.util.Scanner;

public class Vista
{
    private Scanner scanner;

    public Vista()
    {
        this.scanner = new Scanner(System.in);
    }

    // Muestra el tablero en consola
    public void mostrarTablero(Tablero tablero)
    {
        int n = tablero.getDimension();
        System.out.println("\n   ");

        // Encabezado con índices de columnas
        for (int col = 0; col < n; col++)
        {
            System.out.print("    " + (col + 1));
        }

        System.out.println();

        // Mostrar cada fila del tablero
        for (int i = 0; i < n; i++)
        {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < n; j++)
            {
                Ficha ficha = tablero.getFicha(i, j);

                if (ficha.estaEmparejada())
                {
                    System.out.print(" " + ficha.getSimbolo() + "  ");
                }
                
                else if (ficha.estaDescubierta())
                {
                    System.out.print(" " + ficha.getSimbolo() + "  ");
                }
                
                else
                {
                    System.out.print(" [ ] ");
                }
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }

    // Pide al jugador coordenadas de fila y columna
    public int[] leerMovimiento(Jugador jugador, int dimension)
    {
        System.out.println(jugador.getNombre() + ", ingresa fila y columna (ejemplo: 1 1): ");
        int fila = scanner.nextInt() - 1;
        int columna = scanner.nextInt() - 1;
        if (fila < 0 || columna < 0 || fila >= dimension || columna >= dimension) throw new IndexOutOfBoundsException("Coordenadas invalidas");
        return new int[]{fila, columna};
    }

    // Muestra un mensaje en pantalla
    public void mostrarMensaje(String mensaje)
    {
        System.out.println(mensaje);
    }

    // Muestra los puntajes finales
    public void mostrarResultados(Jugador j1, Jugador j2)
    {
        System.out.println("\nResultados finales:");
        System.out.println(j1.getNombre() + ": " + j1.getPuntaje());
        System.out.println(j2.getNombre() + ": " + j2.getPuntaje());
    }

    // Pregunta si se quiere jugar otra partida
    public boolean preguntarNuevaPartida()
    {
        System.out.println("¿Desean jugar otra partida? (s/n): ");
        String respuesta = scanner.next();
        return respuesta.equalsIgnoreCase("s");
    }

    // Lee la dimensión del tablero y valida que sea un número válido
    public int leerDimension()
    {
        System.out.print("Ingrese la dimensión del tablero (ejemplo: 4 o 6): ");
        int d = scanner.nextInt();

        //Verificar que sea >= 2 y par usando división
        if(d < 2 || (d/2)*2 != d)
        {
           throw new IllegalArgumentException("Debe ser par y >= 2");
        }
        return d;
    }

    public void limpiarBuffer()
    {
        scanner.nextLine();
    }
}