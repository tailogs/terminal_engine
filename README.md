# terminal_engine

terminal_engine - мини-движок для текстовых игр в консоли и терминале на C.

![image](https://github.com/user-attachments/assets/ed10748f-cc63-4698-b93f-dedfe24f20dc) <br> Рисунок 1. Интерфейс программы

## Сборка и запуск

Чтобы запустить проект, скачайте один из релизов или скомпилируйте проект сами, используя команды ниже:

1. Перейдите в каталог проекта и выполните команду для сборки:

    ```shell
    make
    ```

2. После успешной сборки вы получите исполняемый файл `terminal_engine.exe`. Запустите его:

    ```shell
    ./terminal_engine.exe
    ```

## Сборка проекта

Если у вас нет релизного файла и вы хотите скомпилировать проект сами, выполните следующие шаги:

1. Убедитесь, что у вас установлены необходимые инструменты для компиляции (например, GCC и `windres`).

2. Используйте `make` для сборки проекта. Команда автоматически создаст объектные файлы и скомпилирует исполняемый файл.

3. В случае, если вам нужно очистить проект от скомпилированных файлов, используйте:

    ```shell
    make clean
    ```

4. Для полной пересборки после очистки выполните:

    ```shell
    make re
    ```
## Документация API

#### Конструкторы `create_player` и `create_enemy`:

```c
Player create_player(const char *name, int hp, int mp, int lvl);
Enemy create_enemy(const char *name, int hp, int mp, int lvl);
```

#### Метод для вывода текста в консоль:

```c
void engine_println_text(Engine *engine, const char *text);
```

#### Статический метод для получения ввода пользователя:

```c
void engine_get_input(const char *prompt, char *input);
```

#### Метод для добавления предмета в инвентарь:

```c
void player_add_item(Player *player, const char *item_name, int item_amount);
```

#### Метод для добавления очков здоровья:

```c
void player_add_hp(Player *player, int hp);
```

#### Метод для проверки статуса жизни:

```c
int player_status_life(const Player *player);
```

#### Метод для вывода статистики игрока:

```c
void player_print_statistics(const Player *player);
```

---