// AumentoAtaque.java
// Aplica un efecto subier el ataque al usuario por varios turnos.

public class AumentoAtaque extends Item
{
    private int incremento;
    private int duracion;

    public AumentoAtaque(int cantidad, int usosRestantes)
    {
        super("Aumento de Ataque", cantidad, usosRestantes);
        this.incremento = 6;
        this.duracion = 3;
    }

    @Override
    public String aplicar(Jugador usuario, Batalla batalla, Combatiente objetivo) {
        AttackUpEffect aue = new AttackUpEffect(duracion, incremento);
        batalla.agregarEfectoEstado(usuario, aue);

        return usuario.getNombre() + " usa Aumento de Ataque (+" + incremento + " ATK por " + duracion + " turnos).";
    }
}