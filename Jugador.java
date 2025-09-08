// Clase Jugador: representa a cada jugador con su nombre y puntaje

public class Jugador
{
    private String nombre;
    private int puntaje;

    public Jugador(String nombre)
    {
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getPuntaje()
    {
        return puntaje;
    }

    // Aumenta el puntaje en 1 (cuando acierta un par)
    public void aumentarPuntaje()
    {
        this.puntaje++;
    }
}