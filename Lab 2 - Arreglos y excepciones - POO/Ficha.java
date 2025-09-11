// Clase Ficha: representa cada ficha en el tablero

public class Ficha
{
    private String simbolo;
    private boolean descubierta;
    private boolean emparejada;

    public Ficha(String simbolo)
    {
        this.simbolo = simbolo;
        this.descubierta = false;
        this.emparejada = false;
    }

    public String getSimbolo()
    {
        return simbolo;
    }

    public boolean estaDescubierta()
    {
        return descubierta;
    }

    public boolean estaEmparejada()
    {
        return emparejada;
    }

    // Marca la ficha como descubierta
    public void descubrir()
    {
        this.descubierta = true;
    }

    // Vuelve a ocultar la ficha
    public void ocultar()
    {
        this.descubierta = false;
    }

    // Marca la ficha como emparejada
    public void marcarEmparejada()
    {
        this.emparejada = true;
    }
}