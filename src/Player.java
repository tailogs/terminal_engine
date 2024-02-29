public class Player extends LivingEntity {
    public Player(String name, Color colorText, int hp, int mp, int lvl) {
        super(name, colorText, hp, mp, lvl);
    }

    public void attack(Enemy enemy, int damage) {
        enemy.addHP(damage);
        System.out.println("Вы атаковали врага и нанесли " + damage + " урона!");
    }
}
