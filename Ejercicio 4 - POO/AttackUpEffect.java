// AttackUpEffect.java
// Aumenta ataque y lo restaura al expirar.

public class AttackUpEffect extends EfectoEstado
{
    private int incremento;

    public AttackUpEffect(int turnos, int incremento)
    {
        super(turnos);
        this.incremento = incremento;
    }

    @Override
    public String alAplicar(Combatiente c, Batalla b)
    {
        c.setPoderAtaque(c.getPoderAtaque() + incremento);
        return c.getNombre() + " recibe +" + incremento + " de ataque.";
    }

    @Override
    public String enTurno(Combatiente c, Batalla b)
    {
        return "";
    }

    @Override
    public void alRemover(Combatiente c, Batalla b)
    {
        c.setPoderAtaque(c.getPoderAtaque() - incremento);
        b.registrarAccion("El aumento de ataque en " + c.getNombre() + " ha terminado.");
    }
}