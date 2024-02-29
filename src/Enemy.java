public class Enemy extends LivingEntity {
    public Enemy(String name, Color colorText, int hp, int mp, int lvl) {
        super(name, colorText, hp, mp, lvl);
    }

    public void attack(Player player, int damage) {
        player.addHP(damage);
        System.out.println("Враг атаковал вас и нанес " + damage + " урона!");
    }
}
