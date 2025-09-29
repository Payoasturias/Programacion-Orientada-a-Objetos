import java.util.ArrayList;

// Explorador.java
// Explorador: vida normal, ataque normal, amplia capacidad de items (10).

public class Explorador extends Jugador
{
    public Explorador(String nombre)
    {
        super(nombre, 10); // capacidad = 5
        this.hp = 140;
        this.poderAtaque = 22;
        // inventario inicial
        this.agregarItem(new HealthPotion(4, 4));
        this.agregarItem(new AumentoAtaque(3, 3));
    }

    @Override
    public ArrayList<String> tomarTurno(Batalla batalla)
    {
        return new ArrayList<String>(); // interacción manejada por Main
    }
}