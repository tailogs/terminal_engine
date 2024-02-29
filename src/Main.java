public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Enemy enemy = new Enemy("Слизень", Color.WHITE, 50, 20, 1);
        Player player = new Player("Игрок", Color.PURPLE, 100, 50, 1);

        // Выводим приветственное сообщение
        engine.printlnText("Добро пожаловать в приключение!");

        // Получаем имя главного героя
        String input = Engine.getInput("Введите имя вашего героя:");
        player.changeName(input);
        engine.printlnText("Привет, " + input + "!");

        // Начало игры
        engine.printlnText("Вы находитесь в темном подземелье. Ваша цель - найти выход.");
        engine.printlnText("Впереди вас раздваивается туннель.");

        // Получаем выбор игрока
        String choice = Engine.getInput("1. Пойти налево. 2. Пойти направо. > ");

        if (choice.equals("1")) {
            engine.printlnText("Вы сворачиваете налево и идете по туннелю.");
            engine.printlnText("Вдруг вы видите дверь в конце туннеля.");

            // Получаем выбор игрока
            String nextChoice = Engine.getInput("1. Открыть дверь. 2. Вернуться обратно. > ");

            if (nextChoice.equals("1")) {
                engine.printlnText("Вы открываете дверь и находите сундук с сокровищами.");
                player.addItem("Сокровища", 1);
            } else if (nextChoice.equals("2")) {
                engine.printlnText("Вы разворачиваетесь и возвращаетесь обратно.");
            }
        } else if (choice.equals("2")) {
            engine.printlnText("Вы сворачиваете направо и продолжаете идти.");
            engine.printlnText("Внезапно вы попадаете в ловушку и теряете часть здоровья.");
            player.addHP(-20);
            boolean status = player.statusLife();
        }

        // Выводим статистику игрока
        player.getStatistics();

        // Завершение игры
        engine.printlnText("Вы достигли конца приключения. Спасибо за игру!");
    }
}
