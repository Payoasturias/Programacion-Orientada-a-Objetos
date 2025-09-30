import java.util.ArrayList;

// Batalla.java
// Estado de batalla + lógica de control.

public class Batalla
{
    private Jugador jugador;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<String> registroAcciones;
    private ArrayList<ArrayList<EfectoEstado>> efectos; // 0 -> jugador, 1..n -> enemigos

    public Batalla(Jugador jugador, ArrayList<Enemigo> enemigos)
    {
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.registroAcciones = new ArrayList<String>();
        this.efectos = new ArrayList<ArrayList<EfectoEstado>>();
        // inicializar listas de efectos paralelas
        efectos.add(new ArrayList<EfectoEstado>()); // jugador

        for (int i = 0; i < enemigos.size(); i++)
        {
            efectos.add(new ArrayList<EfectoEstado>());
        }
    }

    public void registrarAccion(String s)
    {
        if (s == null || s.isEmpty())
        {
            return;
        }

        registroAcciones.add(s);

        if (registroAcciones.size() > 200)
        {
            registroAcciones.remove(0);
        }
    }

    // Agrega efecto al Combatiente correspondiente.
    public void agregarEfectoEstado(Combatiente c, EfectoEstado e)
    {
        int idx = indexOfCombatiente(c);
        if (idx < 0)
        {
            return;
        }

        efectos.get(idx).add(e);
        String r = e.alAplicar(c, this);

        if (r != null && !r.isEmpty())
        {
            registrarAccion(r);
        }
    }

    // Avanza todos los efectos: enTurno, decrementar, remover expirados (llamando alRemover).
    public void avanzarEfectos()
    {
        ArrayList<Combatiente> combatientes = obtenerTodosLosCombatientes();

        for (int i = 0; i < combatientes.size(); i++)
        {
            Combatiente c = combatientes.get(i);
            ArrayList<EfectoEstado> lista = efectos.get(i);
            ArrayList<EfectoEstado> expirados = new ArrayList<EfectoEstado>();

            for (EfectoEstado ef : new ArrayList<EfectoEstado>(lista))
            {
                String r = ef.enTurno(c, this);
                if (r != null && !r.isEmpty()) registrarAccion(r);
                ef.avanzarTurno();
                if (!ef.esActivo()) expirados.add(ef);
            }

            for (EfectoEstado ex : expirados)
            {
                lista.remove(ex);
                ex.alRemover(c, this);
            }
        }
    }

    // Ejecuta turnos de enemigos.
    // Devuelve lista de logs (ya registrados internamente).
    public ArrayList<String> ejecutarTurnosEnemigos()
    {
        ArrayList<String> collected = new ArrayList<String>();
        ArrayList<Enemigo> vivos = obtenerEnemigosVivos();

        for (Enemigo e : vivos)
        {
            ArrayList<String> logs = e.tomarTurno(this);

            for (String s : logs)
            {
                if (s != null && !s.isEmpty()) collected.add(s);
            }
        }

        return collected;
    }

    public ArrayList<Enemigo> obtenerEnemigosVivos()
    {
        ArrayList<Enemigo> vivos = new ArrayList<Enemigo>();
        for (Enemigo e : enemigos) if (e.estaVivo()) vivos.add(e);
        return vivos;
    }

    public ArrayList<Combatiente> obtenerTodosLosCombatientes()
    {
        ArrayList<Combatiente> all = new ArrayList<Combatiente>();
        all.add(jugador);
        for (Enemigo e : enemigos)
        {
            all.add(e);
        }

        return all;
    }

    // Helper: devuelve índice de la lista de efectos según el Combatiente.
    // 0 -> jugador, 1..n -> enemigos

    private int indexOfCombatiente(Combatiente c)
    {
        if (c == jugador)
        {
            return 0;
        }

        for (int i = 0; i < enemigos.size(); i++)
        {
            if (enemigos.get(i) == c)
            {
                return i + 1;
            }
        }

        return -1;
    }

    // Devuelve una lista con los índices de los enemigos vivos en la lista de enemigos
    public ArrayList<Integer> getIndicesEnemigosVivos()
    {
        ArrayList<Integer> indicesVivos = new ArrayList<>();
        for (int i = 0; i < enemigos.size(); i++)
        {
            if (enemigos.get(i).estaVivo())
            {
                indicesVivos.add(i);
            }
        }
        
        return indicesVivos;
    }

    // Devuelve el índice real en la lista de enemigos según la opción elegida (1-based)
    public int getIndiceEnemigoVivoPorOpcion(int opcion)
    {
        ArrayList<Integer> indicesVivos = getIndicesEnemigosVivos();
        if (opcion < 1 || opcion > indicesVivos.size()) 
        {
            return -1;
        }

        return indicesVivos.get(opcion - 1);
    }

    // Muestra los ítems del jugador en consola
    public void mostrarItems(Jugador jugador)
    {
        ArrayList<Item> items = jugador.getItems();
        if (items.isEmpty())
        {
            System.out.println("No tienes ítems disponibles.");
            return;
        }

        System.out.println("Ítems disponibles:");
        for (int i = 0; i < items.size(); i++)
        {
            Item it = items.get(i);
            System.out.println((i + 1) + ". " + it);
        }
    }

    // Getters

    public Jugador getJugador()
    {
        return jugador;
    }

    public ArrayList<Enemigo> getEnemigos()
    {
        return enemigos;
    }

    public ArrayList<String> getRegistroAcciones()
    {
        return registroAcciones;
    }
}