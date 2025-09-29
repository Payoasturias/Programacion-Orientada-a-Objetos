// PoisonEffect.java
// Hace daño por turno.

public class PoisonEffect extends EfectoEstado
{
    private int danoPorTurno;

    public PoisonEffect(int turnos, int danoPorTurno)
    {
        super(turnos);
        this.danoPorTurno = danoPorTurno;
    }

    @Override
    public String alAplicar(Combatiente c, Batalla b)
    {
        return c.getNombre() + " queda envenenado.";
    }

    @Override
    public String enTurno(Combatiente c, Batalla b)
    {
        if (!c.estaVivo())
        {
            return "";
        }

        c.setHp(c.getHp() - danoPorTurno);
        String msg = c.getNombre() + " sufre " + danoPorTurno + " de daño por veneno.";

        if (!c.estaVivo())
        {
            msg += " " + c.getNombre() + " ha muerto por veneno.";
        }

        return msg;
    }
}