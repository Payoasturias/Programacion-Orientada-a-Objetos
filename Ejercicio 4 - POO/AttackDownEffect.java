// AttackDownEffect.java
// Reduce ataque al aplicarse y lo restaura al remover.

public class AttackDownEffect extends EfectoEstado
{
    private int reduccion;

    public AttackDownEffect(int turnos, int reduccion)
    {
        super(turnos);
        this.reduccion = reduccion;
    }

    @Override
    public String alAplicar(Combatiente c, Batalla b)
    {
        c.setPoderAtaque(c.getPoderAtaque() - reduccion);
        return c.getNombre() + " ve su ataque reducido en " + reduccion + ".";
    }

    @Override
    public String enTurno(Combatiente c, Batalla b)
    {
        return "";
    }

    @Override
    public void alRemover(Combatiente c, Batalla b)
    {
        c.setPoderAtaque(c.getPoderAtaque() + reduccion);
        b.registrarAccion("El debilitamiento de ataque en " + c.getNombre() + " ha terminado.");
    }
}