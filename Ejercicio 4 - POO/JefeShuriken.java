// JefeShuriken.java
// JefeShuriken: variante jefe (sobrescribe habilidad especial).

public class JefeShuriken extends LanzadorShuriken
{
    public JefeShuriken(String nombre)
    {
        super(nombre, true);
    }

    @Override
    public String habilidadEspecial(Battle batalla)
    {
        Jugador jugador = batalla.getJugador();

        if (jugador == null || !jugador.estaVivo())
        {
            return "";
        }

        PoisonEffect pe = new PoisonEffect(-1, 12);
        batalla.agregarEfectoEstado(jugador, pe);
        
        return nombre + " (Jefe) usa veneno supremo (ilimitado)";
    }
}
