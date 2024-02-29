import java.util.Scanner;

public abstract class LivingEntity {
    private final static Scanner sc = new Scanner(System.in);
    private final Inventory inventory = new Inventory();
    private String name;
    private final Color colorText;
    private int hp;
    private int mp; // mana points
    private int lvl;
    private int xp; // experience
    private int xpNeeded = 200; // нужно опыта
    public LivingEntity(String name, Color colorText, int hp, int mp, int lvl) {
        this.name = name;
        this.colorText = colorText;
        this.hp = hp;
        this.mp = mp;
        this.lvl = lvl;
    }

    public void addHP(int hp) {
        this.hp += hp;
    }

    public void addMP(int mp) {
        this.mp += mp;
    }

    public void addXP(int xp) {
        this.xp += xp;
        while (this.xp >= xpNeeded) {
            int xpResidual = this.xp - xpNeeded; // Остаточный опыт
            this.xp += xpResidual;
            lvl += 1;
            xpNeeded = (int) (xpNeeded * (lvl + 0.2));
        }
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setMP(int mp) {
        this.mp = mp;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public boolean statusLife() {
        return !(this.hp <= 0);
    }

    public void addItem(String itemName, int itemAmount) {
        inventory.setItem(itemName, itemAmount);
    }

    public void addItem(String text, String itemName, int itemAmount) {
        inventory.setItem(itemName, itemAmount);
        System.out.println(Color.YELLOW.getCode() + "< " + text + " >" + Color.RESET.getCode());
    }

    public void printInventory() {
        System.out.println(colorText.getCode() + "_ Инвентарь _ _ _ _ _ _ _");
        final int[] itemNumber = {0};
        inventory.getInventory().forEach((itemName, itemAmount) -> {
            System.out.println("| -> Номер: " + itemNumber[0] + ", Вещь: " + itemName + ", Количество: " + itemAmount);
            itemNumber[0]++;
        });
        System.out.print(Color.RESET.getCode());
    }


    public void printlnText(String text) {
        System.out.println(colorText.getCode() + name + ": " + text + Color.RESET.getCode());
    }

    public void getStatistics() {
        System.out.println(colorText.getCode() + "_ Статистика _ _ _ _ _ _ _");
        System.out.println("| -> Name: " + name);
        System.out.println("| -> XP: " + xp + "/" + xpNeeded);
        System.out.println("| -> LVL: " + lvl);
        System.out.println("| -> HP: " + hp);
        System.out.println("| -> MP: " + mp);
        System.out.println("| -> LVL: " + lvl);
        System.out.print(Color.RESET.getCode());
    }

    public void attack(LivingEntity le, int damage) {
        le.addHP(damage);
    };
}
