// JefeShadow,java
// JefeShadow: jefe con efectos más fuertes.

public class JefeShadow extends ShadowNinja
{
    public JefeShadow(String nombre)
    {
        super(nombre, true);
    }

    @Override
    public String habilidadEspecial(Batalla batalla)
    {
        String base = super.habilidadEspecial(batalla);
        Jugador j = batalla.getJugador();

        if (j != null && j.estaVivo())
        {
            AttackDownEffect ade = new AttackDownEffect(5, 5);
            batalla.agregarEfectoEstado(j, ade);
        }
        
        return base + " (Jefe aplica debilitamiento fuerte)";
    }
}
