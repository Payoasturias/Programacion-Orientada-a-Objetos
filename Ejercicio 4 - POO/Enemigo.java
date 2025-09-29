import java.util.ArrayList;

// Enemigo.java
// Enemigo abstract class. Enemigos devuelven logs al tomarTurno.

public abstract class Enemigo extends Combatiente
{
    protected String enemigoTipo;
    protected boolean esJefe;

    public Enemigo(String nombre, String enemigoTipo, boolean esJefe, int hp, int atk)
    {
        super(nombre, hp, atk);
        this.enemigoTipo = enemigoTipo;
        this.esJefe = esJefe;
    }

    // Habilidad especial del enemigo (debe devolver un String describiendo lo que hizo)
    public abstract String habilidadEspecial(Batalla batalla);

    // Ataca al jugador o usa habilidad al azar.
    // Devuelve lista de stings (acciones) para ser registradas por Battle.
    @Override
    public ArrayList<String> tomarTurno(Batalla batalla)
    {
        ArrayList<String> logs = new ArrayList<String>();
        Jugador jugador = batalla.getJugador();

        if (jugador == null || !jugador.estaVivo())
        {
            return logs;
        }

        double p = Math.random();

        if (p < 0.6)
        {
            String r = atacar(jugador);
            logs.add(r);
            batalla.registrarAccion(r);
        }

        else
        {
            String r = habilidadEspecial(batalla);

            if (r != null && !r.isEmpty())
            {
                logs.add(r);
                batalla.registrarAccion(r);
            }
        }

        return logs;
    }
}