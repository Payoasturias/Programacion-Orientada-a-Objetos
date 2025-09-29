// Item.java
// Clase abstracta: no imprime; aplicar devuelve String describiendo su efecto.

public abstract class Item
{
    private String nombre;
    private int cantidad;       // unidades en inventario
    private int usosRestantes;  // usos totales (por unidad o total según tu diseño)

    public Item(String nombre, int cantidad, int usosRestantes)
    {
        this.nombre = nombre;
        this.cantidad = Math.max(0, cantidad);
        this.usosRestantes = Math.max(0, usosRestantes);
    }

    // Consumir: reduce cantidad y usosRestantes.
    public void consumir()
    {
        if (cantidad > 0) 
        {
            cantidad--;
        }

        if (usosRestantes > 0) 
        {
            usosRestantes--;
        }
    }

    // Aplica el efecto: devuelve String describiendo lo que hizo.
    // usuario = quien usa el item (Jugador)
    // objetivo = combatiente objetivo (puede ser el propio usuario o un enemigo)
    public abstract String aplicar(Jugador usuario, Batalla batalla, Combatiente objetivo);

    // Getters y Setters

    public String getNombre()
    {
        return nombre;
    }

    public int getCantidad()
    {
        return cantidad; 
    }

    public int getUsosRestantes()
    {
        return usosRestantes;
    }
}