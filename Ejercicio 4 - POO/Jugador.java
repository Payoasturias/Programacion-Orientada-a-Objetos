import java.util.ArrayList;

// Jugador.java
// Clase base abstracta para jugadores. Subclases concretas: Guerrero, Explorador.

public abstract class Jugador extends Combatiente
{
    private int experiencia;
    private int nivel;
    private int monedas;
    private int capacidadItems;
    private ArrayList<Item> items;
    
    // Se quito la variable rol, porque me tuve la idea de hacer una clase rol, pero mejor separe en dos las clases explorador y guerrero.

    // Constructor de Jugador
    public Jugador(String nombre, int capacidadItems)
    {
        super(nombre, 0, 0);
        this.experiencia = 0;
        this.nivel = 1;
        this.monedas = 0;
        // Capacidad de Items dependiendo del Jugador
        this.capacidadItems = Math.max(0, capacidadItems);
        this.items = new ArrayList<>();
    }

    // Getters / Operaciones sobre inventario (usando ArrayList)

    public int getExperiencia()
    {
        return experiencia;
    }

    public int getNivel()
    {
        return nivel;
    }

    public int getMonedas()
    {
        return monedas;
    }

    // Devuelve el ArrayList de items (mutable)
    public ArrayList<Item> getItems()
    {
        return items;
    }

    public int getCapacidadItems()
    {
        return capacidadItems;
    }

    // Agrega un item si hay espacio (capacidad).
    // Devuelve true si se añadió.
    public boolean agregarItem(Item item)
    {
        if (item == null)
        {
            return false;
        }

        if (items.size() >= capacidadItems)
        {
            return false;
        }

        items.add(item);
        return true;
    }

    // Elimina item por índice. Devuelve true si eliminado.
    public boolean eliminarItem(int indice)
    {
        if (indice < 0 || indice >= items.size())
        {
            return false;
        }
        
        items.remove(indice);
        return true;
    }

    // Usar un item (el item se encarga de su efecto). Consumir decrementa usos/cantidad. Devuelve true si se aplico correctamente.
    public boolean usarItem(Item item, Batalla batalla, Combatiente objetivo)
    {
        if (item == null || !items.contains(item))
        {
            return false;
        }

        String res = item.aplicar(this, batalla, objetivo); // aplicar registra internamente en batalla si corresponde
        batalla.registrarAccion(res);
        item.consumir();

        if (item.getUsosRestantes() <= 0 && item.getCantidad() <= 0)
        {
            // quitar del inventario
            items.remove(item);
        }

        return true;
    }

    // Progresion

    public void subirNivel()
    {
        nivel++;
        hp += 10;
        poderAtaque += 2;
    }

    public void ganarExperiencia(int cantidad)
    {
        experiencia += cantidad;
        if (experiencia >= nivel*100)
        {
            experiencia -= nivel*100;
            subirNivel();
        }
    }

    public void ganarMonedas(int cantidad)
    {
        monedas += cantidad;
    }

    @Override
    public String mostrarMensaje(String contexto)
    {
        if ("start".equals(contexto))
        {
            return nombre + ": \"¡Voy con todo!\"";
        }

        if ("win".equals(contexto))
        {
            return nombre + ": \"¡Victoria!\"";
        }

        if ("die".equals(contexto))
        {
            return nombre + ": \"No...\"";
        }

        return "";
    }

    // Override al atacar para jugadores: reutiliza logica base pero devuelve string.
    @Override
    public String atacar(Combatiente objetivo)
    {
        if (!this.estaVivo() || objetivo == null || !objetivo.estaVivo())
        {
            return "";
        }

        String base = super.atacar(objetivo);
        return base + " (" + this.nombre + " como jugador).";
    }

    // Las subclases (Guerrero/Explorador) implementan la logica del turno y devuelven logs.
    @Override
    public abstract ArrayList<String> tomarTurno(Batalla batalla);

    // Implementación del método getTag() para la clase Jugador.
    // Devuelve la etiqueta "[Jugador]" para identificar a este combatiente
    @Override
    public String getTag()
    {
        return " [Jugador]";
    }
}
