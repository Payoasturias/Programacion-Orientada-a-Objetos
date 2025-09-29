// EfectoEstado.java
// Efecto base. turnosRestantes == -1 => ilimitado.

public abstract class EfectoEstado
{
    protected int turnosRestantes;

    public EfectoEstado(int turnos)
    {
        this.turnosRestantes = turnos;
    }

    public int getTurnosRestantes()
    {
        return turnosRestantes;
    }

    public void avanzarTurno()
    {
        if (turnosRestantes > 0) 
        {
            turnosRestantes--;
        }
    }

    public abstract String alAplicar(Combatiente c, Batalla b);

    public abstract String enTurno(Combatiente c, Batalla b);

    public void alRemover(Combatiente c, Batalla b) { /* opcional sobrescribir */ }

    // -1 = activo indefinidamente
    public boolean esActivo()
    {
        return turnosRestantes != 0;
    }
}