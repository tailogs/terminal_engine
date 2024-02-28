public class Main {
    public static void main(String[] args) {
        Engine hero = new Engine("Hero", Color.GREEN, 200, 100, 1);
        Engine enemy = new Engine("Enemy", Color.RED, 100, 50, 1);
        Engine.printText("==================\n");
        hero.printlnText("Привет!");
        enemy.printlnText("Что тебя сюда привело?");
        hero.printlnText("Ничего такого, я просто пришел за ключом");
        enemy.printlnText("А, ну укради его у меня");
        hero.addItem("Вы украли 2 ключа", "Ключ", 2);
        hero.addItem("Вы украли 3 булочки", "Булочка", 3);
        hero.addItem("Вы украли 2 булочки", "Булочка", 2);
        hero.addItem("Бумажка", 5);
        hero.printInventory();
        enemy.printInventory();
        Engine.printText("===", 10, "\n");
        hero.getStatistics();
        enemy.getStatistics();

    }
}