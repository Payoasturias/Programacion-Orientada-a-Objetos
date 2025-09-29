import java.util.ArrayList;

// Battle.java
// Modelo central: jugador, enemigos, registro de acciones, efectos.

public class Battle
{
    private Jugador jugador;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<String> registroAcciones;
    private ArrayList<ArrayList<EfectoEstado>> efectos; // paralelo: 0->Jugador, 1..n->enemigos

    public Batalla(Jugador jugador, ArrayList<Enemigo> enemigos)
    {
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.registroAcciones = new ArrayList<String>();
        this.efectos = new ArrayList<ArrayList<EfectoEstado>>();

        efectos.add(new ArrayList<EfectoEstado>()); // Jugador

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

        if (registroAcciones.size() > 50)
        {
            registroAcciones.remove(0);
        }
    }

    // Agrega efecto a combatiente (Jugador->idx0, Enemigo->idx+1)
    public void agregarEfectoEstado(Combatiente c, EfectoEstado e) {
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

    // Avanza efectos: llama enTurno, decrece turnos y remueve expirados
    public void avanzarEfectos()
    {
        ArrayList<Combatiente> combatientes = obtenerTodosLosCombatientes();

        for (int i = 0; i < combatientes.size(); i++)
        {
            Combatiente c = combatientes.get(i);
            ArrayList<EfectoEstado> lista = efectos.get(i);
            ArrayList<EfectoEstado> expirados = new ArrayList<EfectoEstado>();
            
            for (EfectoEstado ef : lista)
            {
                String r = ef.enTurno(c, this);

                if (r != null && !r.isEmpty())
                {
                    registrarAccion(r);
                }

                ef.avanzarTurno();

                if (!ef.esActivo())
                {
                    expirados.add(ef);
                }
            }

            for (EfectoEstado ex : expirados)
            {
                lista.remove(ex);
                ex.alRemover(c, this);
            }
        }
    }

    public ArrayList<Enemigo> obtenerEnemigosVivos()
    {
        ArrayList<Enemigo> vivos = new ArrayList<Enemigo>();

        for (Enemigo e : enemigos)
        {
            if (e.estaVivo())
            {
                vivos.add(e);
            }
        }

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
