import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Main.java
// Punto de entrada: maneja el menú, imprime estado y registra/consume acciones.
// ÚNICA clase que imprime en consola.

public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Naruto Shippuden - Prototipo Batalla por Turnos ===");
        System.out.print("Introduce nombre del jugador: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) nombre = "Jugador";

        int opcion = 0;
        while (opcion != 1 && opcion != 2) {
            System.out.println("Elige rol:");
            System.out.println("1) Guerrero (vida alta, ataque alto, capacidad de ítems = 5)");
            System.out.println("2) Explorador (vida normal, ataque normal, capacidad de ítems = 10)");
            System.out.print("Opción (1-2): ");
            try { opcion = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) { opcion = 0; }
        }

        Jugador jugador;
        if (opcion == 1) jugador = new Guerrero(nombre);
        else jugador = new Explorador(nombre);

        // Generar enemigos aleatorios 1..3
        Random rnd = new Random();
        int cantidad = rnd.nextInt(3) + 1;
        ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
        for (int i = 0; i < cantidad; i++) {
            boolean shuriken = rnd.nextBoolean();
            boolean jefe = rnd.nextBoolean();
            if (shuriken) {
                if (jefe) enemigos.add(new JefeShuriken("Jefe Shuriken " + (i+1)));
                else enemigos.add(new LanzadorShuriken("Shuriken " + (i+1), false));
            } else {
                if (jefe) enemigos.add(new JefeShadow("Jefe Sombra " + (i+1)));
                else enemigos.add(new ShadowNinja("Sombra " + (i+1), false));
            }
        }

        Batalla batalla = new Batalla(jugador, enemigos);
        BatallaController controlador = new BatallaController(batalla);

        // Registrar mensajes de inicio
        batalla.registrarAccion(jugador.mostrarMensaje("start"));
        for (Enemigo e : enemigos) batalla.registrarAccion(e.mostrarMensaje("start"));

        boolean salir = false;
        // Bucle principal: se cumple la condición de terminación sin usar while(true)+break
        while (!salir && jugador.estaVivo() && !batalla.obtenerEnemigosVivos().isEmpty()) {

            // Mostrar estado
            System.out.println("\n--- Estado ---");
            ArrayList<Combatiente> todos = batalla.obtenerTodosLosCombatientes();
            for (Combatiente c : todos) {
                System.out.println(c.toString() + (c instanceof Jugador ? " [Jugador]" : ""));
            }

            // Mostrar últimas 3 acciones
            System.out.println("\n--- Registro (últimas 3) ---");
            ArrayList<String> log = batalla.getRegistroAcciones();
            int start = Math.max(0, log.size() - 3);
            for (int i = start; i < log.size(); i++) System.out.println(" * " + log.get(i));

            // Menú del jugador
            System.out.println("\n--- Tu turno: " + jugador.getNombre() + " ---");
            System.out.println("1) Atacar");
            System.out.println("2) Usar ítem");
            System.out.println("3) Pasar turno");
            System.out.println("4) Salir de la batalla");
            System.out.print("Elige opción (1-4): ");
            int accion = 0;
            try { accion = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) { accion = 0; }

            if (accion == 1) {
                // Atacar: elegir enemigo objetivo
                ArrayList<Enemigo> vivos = batalla.obtenerEnemigosVivos();
                if (vivos.isEmpty()) {
                    batalla.registrarAccion("No hay enemigos para atacar.");
                } else {
                    System.out.println("Elige enemigo a atacar:");
                    for (int i = 0; i < vivos.size(); i++) System.out.println((i+1) + ") " + vivos.get(i).getNombre());
                    int sel = 0;
                    try { sel = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) { sel = 0; }
                    if (sel < 1 || sel > vivos.size()) {
                        System.out.println("Selección inválida.");
                        batalla.registrarAccion(jugador.getNombre() + " intentó atacar pero escogió mal.");
                    } else {
                        Enemigo objetivo = vivos.get(sel - 1);
                        String res = jugador.atacar(objetivo);
                        batalla.registrarAccion(res);
                    }
                }
            } else if (accion == 2) {
                // Usar ítem
                ArrayList<Item> inv = jugador.getItems();
                if (inv.isEmpty()) {
                    batalla.registrarAccion("No tienes ítems disponibles.");
                } else {
                    System.out.println("Inventario:");
                    for (int i = 0; i < inv.size(); i++) {
                        Item it = inv.get(i);
                        System.out.println((i+1) + ") " + it.getNombre() + " (cant: " + it.getCantidad() +
                                ", usos: " + it.getUsosRestantes() + ")");
                    }
                    System.out.print("Elige número de ítem: ");
                    int sel = 0;
                    try { sel = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) { sel = 0; }
                    if (sel < 1 || sel > inv.size()) {
                        System.out.println("Ítem inválido.");
                        batalla.registrarAccion(jugador.getNombre() + " intentó usar un ítem inválido.");
                    } else {
                        // elegir objetivo: Yo mismo o enemigo
                        System.out.println("Objetivo:");
                        System.out.println("1) Yo mismo");
                        ArrayList<Enemigo> vivos = batalla.obtenerEnemigosVivos();
                        for (int i = 0; i < vivos.size(); i++) System.out.println((i+2) + ") " + vivos.get(i).getNombre());
                        int tgt = 0;
                        try { tgt = Integer.parseInt(scanner.nextLine().trim()); } catch (Exception e) { tgt = 0; }
                        Combatiente objetivo = null;
                        if (tgt == 1) objetivo = jugador;
                        else if (tgt >= 2 && tgt <= vivos.size() + 1) objetivo = vivos.get(tgt - 2);
                        else objetivo = null;

                        boolean ok = jugador.usarItem(sel - 1, batalla, objetivo);
                        if (!ok) {
                            batalla.registrarAccion(jugador.getNombre() + " falló al intentar usar el ítem.");
                        }
                    }
                }
            } else if (accion == 3) {
                batalla.registrarAccion(jugador.getNombre() + " decide pasar el turno.");
            } else if (accion == 4) {
                salir = true;
                batalla.registrarAccion(jugador.getNombre() + " ha decidido retirarse.");
            } else {
                System.out.println("Opción inválida.");
            }

            // Enemigos realizan sus turnos (si el jugador aún vive)
            if (jugador.estaVivo()) {
                controlador.ejecutarTurnosEnemigos();
            }

            // Avanzar efectos y registrar sus logs
            batalla.avanzarEfectos();
        }

        // Resultado final, mostrado en Main
        System.out.println("\n--- Resultado ---");
        if (!jugador.estaVivo()) {
            System.out.println(jugador.mostrarMensaje("die"));
        } else if (batalla.obtenerEnemigosVivos().isEmpty()) {
            System.out.println(jugador.mostrarMensaje("win"));
        } else if (salir) {
            System.out.println("Batalla finalizada por decisión del jugador.");
        }

        // Mostrar registro completo al finalizar (opcional)
        System.out.println("\n--- Registro completo ---");
        for (String s : batalla.getRegistroAcciones()) System.out.println(" * " + s);

        scanner.close();
    }
}
