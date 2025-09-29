// ShadowNinja.java
// ShadowNinja: evasión y debilitamiento.

public class ShadowNinja extends Enemigo
{
    protected boolean evasivoProximoTurno;

    public ShadowNinja(String nombre, boolean esJefe)
    {
        super(nombre, "ShadowNinja", esJefe, esJefe ? 130 : 75, esJefe ? 20 : 10);
        this.evasivoProximoTurno = false;
    }

    public boolean intentarEvadir()
    {
        if (evasivoProximoTurno)
        {
            evasivoProximoTurno = false;
            return true;
        }
        return Math.random() < 0.1;
    }

    @Override
    public String habilidadEspecial(Battle batalla)
    {
        this.evasivoProximoTurno = true;
        String mensaje = nombre + " se vuelve evasivo (próximo turno).";

        if (esJefe)
        {
            Jugador j = batalla.getJugador();

            if (j != null && j.estaVivo())
            {
                AttackDownEffect ade = new AttackDownEffect(3, 3);
                batalla.agregarEfectoEstado(j, ade);
                mensaje += " Aplica debilitamiento al jugador.";
            }
        }
        return mensaje;
    }

    @Override
    public String atacar(Combatiente objetivo)
    {
        // si intenta evadir, puede evitar ser dañado (esto se aplica en Battle al calcular daño).
        return super.atacar(objetivo);
    }
}
