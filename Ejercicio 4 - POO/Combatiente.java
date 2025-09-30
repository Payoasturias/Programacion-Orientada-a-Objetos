// Combatiente.java
// Clase base para cualquier combatiente (jugador o enemigo).

public abstract class Combatiente
{
    protected String nombre;
    protected int hp;
    protected int poderAtaque;

    public Combatiente(String nombre, int hp, int poderAtaque)
    {
        this.nombre = nombre;
        this.hp = Math.max(0, hp);
        this.poderAtaque = poderAtaque;
    }

    // Devuelve un mensaje textual del contexto (start/win/die).
    public String mostrarMensaje(String contexto)
    {
        if ("start".equals(contexto))
        {
            return nombre + " entra en batalla.";
        }

        if ("win".equals(contexto))
        {
            return nombre + " ha ganado la batalla.";
        }

        if ("die".equals(contexto))
        {
            return nombre + " ha sido derrotado.";
        }

        return "";
    }

    public boolean estaVivo()
    {
        return hp > 0;
    }

    // Ataque básico: reduce HP del objetivo. Devuelve una cadena describiendo la acción.
    public String atacar(Combatiente objetivo)
    {
        if (!estaVivo() || objetivo == null || !objetivo.estaVivo())
        {
            return "";
        }

        int dano = this.poderAtaque;
        objetivo.setHp(objetivo.getHp() - dano);
        String mensaje = this.nombre + " ataca a " + objetivo.getNombre() + " por " + dano + " de daño.";

        if (!objetivo.estaVivo())
        {
            mensaje += " " + objetivo.getNombre() + " ha caído.";
        }

        return mensaje;
    }

    // Cada subclase define como toma su turno (puede devolver una lista de acciones o registro.)
    public abstract java.util.ArrayList<String> tomarTurno(Batalla batalla);

    // Devuelve una etiqueta identificadora del tipo de combatiente.
    public abstract String getTag();

    // Getters y Setters

    public String getNombre()
    {
        return nombre;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = Math.max(0, hp);
    }

    public int getPoderAtaque()
    {
        return poderAtaque;
    }

    public void setPoderAtaque(int p)
    {
        this.poderAtaque = p;
    }
}