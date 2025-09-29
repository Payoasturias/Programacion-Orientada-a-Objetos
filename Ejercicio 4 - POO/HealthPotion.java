// HealthPotion.java
// Cura al usuario cuando se aplica.

public class HealthPotion extends Item
{
    private int healthAmount;

    public HealthPotion(int cantidad, int usosRestantes)
    {
        super("Poción de Vida", cantidad, usosRestantes);
        this.healthAmount = 40;
    }

    @Override
    public String aplicar(Jugador usuario, Batalla batalla, Combatiente objetivo)
    {
        int actualHealth = healthAmount;
        usuario.setHp(usuario.getHp() + actualHealth);
        String msg = usuario.getNombre() + " usa Poción de Vida y recupera " + actualHealth + " HP.";
        return msg;
    }
}