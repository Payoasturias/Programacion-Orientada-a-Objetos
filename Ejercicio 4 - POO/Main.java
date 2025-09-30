// Programación Orientada a Objetos
// Universidad del Valle de Guatemala
// Sección 40
// Ejercicio 4 - Modelación con Herencia
// Paolo Asturias
// Carné: 25722

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Main.java
// ÚNICA clase que imprime en consola. Orquesta selección de rol, menú y flujo de batalla.

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Crear jugador
        System.out.println("=== BIENVENIDO AL COMBATE NARUTO SHIPPUDEN ===");
        System.out.print("Ingrese el nombre de su personaje: ");
        String nombre = sc.nextLine();

        if (nombre.isEmpty()) 
        {
            nombre = "Jugador";
        }

        System.out.println("Seleccione su rol:");
        System.out.println("1. Guerrero (más vida y ataque, menos ítems)");
        System.out.println("2. Explorador (vida y ataque normales, más ítems)");
        int rol = sc.nextInt();
        sc.nextLine();

        Jugador jugador;
        if (rol == 1)
        {
            jugador = new Guerrero(nombre);
        }
        
        else
        {
            jugador = new Explorador(nombre);
        }

        // Crear enemigos aleatorios entre 1 y 3
        int cantidadEnemigos = (int) (Math.random() * 3) + 1;
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < cantidadEnemigos; i++)
        {
            double tipo = Math.random(); // tipo de enemigo (thrower o shadow)
            boolean esJefe = Math.random() < 0.25; // 25% de probabilidad de que sea jefe

            if (tipo < 0.5)
            {
                enemigos.add(esJefe ? new JefeShuriken("Jefe Shuriken" + (i + 1)) : new LanzadorShuriken("Lanzador " + (i + 1), false));
            }
            
            else
            {
                enemigos.add(esJefe ? new JefeShadow("Jefe Shadow" + (i + 1)) : new ShadowNinja("Sombra " + (i + 1), false));
            }
        }

        // Crear batalla
        Batalla batalla = new Batalla(jugador, enemigos);

        // Bucle principal de combate
        while (jugador.estaVivo() && !batalla.obtenerEnemigosVivos().isEmpty())
        {
            System.out.println("\n===== ESTADO DE LA BATALLA =====");
            System.out.println(jugador.getNombre() + jugador.getTag() + " - HP: " + jugador.getHp());

            for (Enemigo e : enemigos)
            {
                if (e.estaVivo())
                {
                    System.out.println(e.getNombre() + e.getTag() + " - HP: " + e.getHp());
                }
            }

            System.out.println("\n--- Turno de " + jugador.getNombre() + jugador.getTag() + " ---");
            System.out.println("1. Atacar");
            System.out.println("2. Usar ítem");
            System.out.print("Elija una acción: ");
            int opcion = sc.nextInt();

            if (opcion == 1)
            {
                // Mostrar enemigos vivos y pedir opción
                ArrayList<Integer> indicesVivos = batalla.getIndicesEnemigosVivos();
                if (indicesVivos.isEmpty())
                {
                    System.out.println("No hay enemigos vivos.");
                }
                
                else
                {
                    System.out.println("Seleccione el enemigo a atacar:");
                    for (int i = 0; i < indicesVivos.size(); i++)
                    {
                        Enemigo e = enemigos.get(indicesVivos.get(i));
                        System.out.println((i + 1) + ". " + e.getNombre() + e.getTag() + " - HP: " + e.getHp());
                    }

                    int opcionEnemigo = -1;
                    while (opcionEnemigo < 1 || opcionEnemigo > indicesVivos.size())
                    {
                        System.out.print("Ingrese el número del enemigo: ");
                        if (sc.hasNextInt())
                        {
                            opcionEnemigo = sc.nextInt();
                        }
                        
                        else
                        {
                            sc.next();
                            opcionEnemigo = -1;
                        }
                    }

                    int objetivo = batalla.getIndiceEnemigoVivoPorOpcion(opcionEnemigo);
                    if (objetivo != -1)
                    {
                        jugador.atacar(enemigos.get(objetivo));
                    }
                }
            }
            
            else if (opcion == 2)
            {
                batalla.mostrarItems(jugador);
                System.out.print("Seleccione el número de ítem: ");
                int idx = sc.nextInt();
                ArrayList<Integer> indicesVivos2 = batalla.getIndicesEnemigosVivos();
                if (indicesVivos2.isEmpty())
                {
                    System.out.println("No hay enemigos vivos.");
                }
                
                else
                {
                    System.out.println("Seleccione el enemigo a atacar:");
                    for (int i = 0; i < indicesVivos2.size(); i++)
                    {
                        Enemigo e = enemigos.get(indicesVivos2.get(i));
                        System.out.println((i + 1) + ". " + e.getNombre() + e.getTag() + " - HP: " + e.getHp());
                    }

                    int opcionEnemigo = -1;
                    while (opcionEnemigo < 1 || opcionEnemigo > indicesVivos2.size())
                    {
                        System.out.print("Ingrese el número del enemigo: ");
                        String input = sc.nextLine();
                        try
                        {
                            opcionEnemigo = Integer.parseInt(input);
                        }
                        
                        catch (NumberFormatException e)
                        {
                            opcionEnemigo = -1;
                        }
                    }

                    int objetivo = batalla.getIndiceEnemigoVivoPorOpcion(opcionEnemigo);
                    if (objetivo != -1)
                    {
                        Item item = jugador.getItems().get(idx - 1); // si el usuario elige desde 1
                        jugador.usarItem(item, batalla, enemigos.get(objetivo));
                    }
                }
            }

            // Turnos enemigos
            for (Enemigo e : enemigos)
            {
                if (e.estaVivo())
                {
                    System.out.println("\n--- Turno de " + e.getNombre() + e.getTag() + " ---");
                    e.tomarTurno(batalla);
                }
            }

            // Actualizar efectos de estado, si hay
            batalla.avanzarEfectos();
        }

        // Resultado final
        System.out.println("\n===== RESULTADO =====");
        if (jugador.estaVivo())
        {
            System.out.println("¡Has ganado el combate!");
        }
        
        else
        {
            System.out.println("Has sido derrotado...");
        }

        sc.close();
    }
}