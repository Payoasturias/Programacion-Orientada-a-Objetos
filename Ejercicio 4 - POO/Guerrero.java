import java.util.ArrayList;

// Guerrero.java
// Guerrero: vida alta, ataque alto, poca capacidad de items (5).

public class Guerrero extends Jugador
{
    public Guerrero(String nombre)
    {
        super(nombre, 5); // capacidad = 5
        this.hp = 200;
        this.poderAtaque = 35;
        // inventario inicial
        this.agregarItem(new HealthPotion(2, 2));
        this.agregarItem(new AumentoAtaque(1, 1));
    }

    // Cuando es turno del jugador, la logica real dell menú la maneja Main.
    // Aquí solo devolvemos una lista vacía si no hay acciones automáticas.
    @Override
    public ArrayList<String> tomarTurno(Batalla batalla)
    {
        return new ArrayList<String>(); // Main manejará la interacción
    }
}