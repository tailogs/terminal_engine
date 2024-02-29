# TextRPG
TextRPG - мини движок для текстовых игр в консоли и терминале 

![image](https://github.com/tailogs/TextRPG/assets/69743960/efac8d08-3700-4635-90f6-fd88dae76e90)

Для запуска скачайте один из релизов или скомпилируйте сам проект используя комманды ниже.

1. Перейдите в каталог прокта и введите следующую командуд

```shell
javac -d bin ./src/*
```

2. Далее введите эту команду и если код запуститься, значит все работает

```shell
java -classpath .\bin Main
```

3. Скомпилируйте программу в `jar` файл

```shell
 jar -cmf .\manifest.mf TextRPG.jar -C bin .
```

4. Запуск программы осуществляется командой

```shell
java -jar .\TextRPG.jar
```

Теперь вы можете распространять `TextRPG.jar` файл, 
но чтобы другой человек его запустил вам нужно показать последнюю команду выше.
Его задача вводить эту команду для запуска программы.
Так же у него должна быть `jvm java` и очень желательно `21` версии.

---

[Статья для тех кто не понял как компилировать](https://javarush.com/groups/posts/2318-kompiljacija-v-java)

---

## Краткая документация к API библиотеки:

#### Конструкторы класса `Player` и `Enemy` наследуются от класса `LivingEntity`, который принимает следующие параметры:
- имя существа,
- цвет текста,
- количество очков здоровья (hp),
- количество очков маны (mp),
- уровень (lvl).

```java
LivingEntity(String playerName, Color colorText, int hp, int mp, int lvl);
```

#### Метод, выводящий текст в консоль с указанным цветом и именем игрока. Такой же метод есть у класса `Engine`, но вывод осуществляется там без имени.

```java
printlnText(String text);
```

#### Статический метод у класса `Engine`, выводящий текст в консоль без перевода строки

```java
printText(String text);
```

#### Статический метод у класса `Engine`, выводящий текст в консоль заданное количество раз с указанным окончанием

```java
printText(String text, int amount, String endText);
```

#### Метод класса `LivingEntity`, добавляющий предмет в инвентарь с указанным именем и количеством

```java
addItem(String itemName, int itemAmount);
```

#### Метод класса `LivingEntity`, добавляющий предмет в инвентарь с указанным именем, количеством и выводящий текст в консоль

```java
addItem(String text, String itemName, int itemAmount);
```

#### Метод класса `LivingEntity`, выводящий содержимое инвентаря в консоль

```java
printInventory();
```

#### Метод класса `LivingEntity`, выводящий статистику игрока в консоль

```java
getStatistics();
```

#### Метод класса `LivingEntity`, добавляющий количество очков здоровья игрока на указанное значение

```java
addHP(int hp);
```

#### Метод класса `LivingEntity`, добавляющий количество очков маны игрока на указанное значение

```java
addMP(int mp);
```

#### Метод класса `LivingEntity`, добавляющий количество опыта игрока на указанное значение и автоматически повышающий уровень при достижении необходимого количества опыта

```java
addXP(int xp);
```

#### Метод класса `LivingEntity`, устанавливающий количество очков здоровья игрока на указанное значение

```java
setHP(int hp);
```

#### Метод класса `LivingEntity`, устанавливающий количество очков маны игрока на указанное значение

```java
setMP(int mp);
```

#### Статический метод у класса `Engine`, получающий ввод пользователя и возвращающий его как строку

```java
getInput();
```

#### Статический метод у класса `Engine`, выводящий текст и получающий ввод пользователя и возвращающий его как строку

```java
getInput(String text);
```

#### Метод класса `LivingEntity`, позволяющий изменить имя персонажа

```java
changeName(String name);
```

#### Метод класса `LivingEntity`, позволяющий узнать жив ли персонаж, обычно применяемые после метода `addHP()`. Если персонаж живой, то вернется `true`, в противном случае `false`

```java
statusLife();
```

#### Метод класса `LivingEntity`, позволяющий атаковать врага. Он переписывается у дочерных классов

```java
public void attack(LivingEntity le, int damage);
```

## Пример кода

```java
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


```
