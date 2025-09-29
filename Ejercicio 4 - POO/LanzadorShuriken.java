// LanzadorShuriken.java
// LanzadorShuriken: 

public class LanzadorShuriken extends Enemigo
{
    public LanzadorShuriken(String nombre, boolean esJefe)
    {
        super(nombre, "LanzadorShuriken", esJefe, esJefe? 140 : 80, esJefe ? 22 : 12);
    }

    @Override
    public String habilidadEspecial(Batalla batalla)
    {
        Jugador jugador = batalla.getJugador();

        if (jugador == null || !jugador.estaVivo())
        {
            return "";
        }

        int turnos = esJefe ? -1 : 3;
        int dano = esJefe ? 10 : 5;
        PoisonEffect pe = new PoisonEffect(turnos, dano);
        batalla.agregarEfectoEstado(jugador, pe);
        
        return nombre + " usa veneno(" + (turnos < 0 ? "ilimitado" : turnos + " turnos") + ")";
    }
}