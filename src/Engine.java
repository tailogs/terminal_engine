public class Engine {
    private Inventory inventory = new Inventory();
    private String playerName;
    private Color colorText;
    private int hp;
    private int mp; // mana points
    private int lvl;
    private int xp; // experience
    private int xpNeeded = 200; // нужно опыта

    Engine(String playerName, Color colorText, int hp, int mp, int lvl) {
        this.playerName = playerName;
        this.colorText = colorText;
        this.hp = hp;
        this.mp = mp;
        this.lvl = lvl;
    }

    public void printlnText(String text) {
        System.out.println(colorText.getCode() + playerName + ": " + text + Color.RESET.getCode());
    }

    public static void printText(String text) {
        System.out.print(text);
    }

    public static void printText(String text, int amount, String endText) {
        for (int i = 0; i < amount; i++) System.out.print(text);
        System.out.print(endText);
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

    public void getStatistics() {
        System.out.println(colorText.getCode() + "_ Статистика _ _ _ _ _ _ _");
        System.out.println("| -> Name: " + playerName);
        System.out.println("| -> XP: " + xp + "/" + xpNeeded);
        System.out.println("| -> LVL: " + lvl);
        System.out.println("| -> HP: " + hp);
        System.out.println("| -> MP: " + mp);
        System.out.println("| -> LVL: " + lvl);
        System.out.print(Color.RESET.getCode());
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
}
