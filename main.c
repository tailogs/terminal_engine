#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>

// ����������� ������ ANSI
typedef enum {
    RESET, BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE
} Color;

const char* getColorCode(Color color) {
    switch (color) {
        case RESET:   return "\033[0m";
        case BLACK:   return "\033[30m";
        case RED:     return "\033[31m";
        case GREEN:   return "\033[32m";
        case YELLOW:  return "\033[33m";
        case BLUE:    return "\033[34m";
        case PURPLE:  return "\033[35m";
        case CYAN:    return "\033[36m";
        case WHITE:   return "\033[37m";
        default:      return "\033[0m"; // Default to RESET
    }
}

// ��������� � ������� ��� ���������
#define MAX_ITEMS 100

typedef struct {
    char* itemName;
    int itemAmount;
} InventoryItem;

typedef struct {
    InventoryItem items[MAX_ITEMS];
    int size;
} Inventory;

void initInventory(Inventory* inventory) {
    inventory->size = 0;
}

static int findItemIndex(const Inventory* inventory, const char* itemName) {
    for (int i = 0; i < inventory->size; i++) {
        if (strcmp(inventory->items[i].itemName, itemName) == 0) {
            return i;
        }
    }
    return -1; // ���� ������� �� ������
}

void setItem(Inventory* inventory, const char* itemName, int itemAmount) {
    int index = findItemIndex(inventory, itemName);
    if (index != -1) {
        inventory->items[index].itemAmount += itemAmount;
    } else {
        if (inventory->size < MAX_ITEMS) {
            inventory->items[inventory->size].itemName = strdup(itemName);
            inventory->items[inventory->size].itemAmount = itemAmount;
            inventory->size++;
        } else {
            printf("��������� �����!\n");
        }
    }
}

int getAmount(const Inventory* inventory, const char* itemName) {
    int index = findItemIndex(inventory, itemName);
    if (index != -1) {
        return inventory->items[index].itemAmount;
    } else {
        return 0;
    }
}

int getItemsAmount(const Inventory* inventory) {
    return inventory->size;
}

const InventoryItem* getInventory(const Inventory* inventory) {
    return inventory->items;
}

// ��������� � ������� ��� ����� �������
typedef struct {
    char* name;
    Color colorText;
    int hp;
    int mp;
    int lvl;
    int xp;
    int xpNeeded;
    Inventory inventory;
} LivingEntity;

void initLivingEntity(LivingEntity* le, const char* name, Color colorText, int hp, int mp, int lvl) {
    le->name = strdup(name);
    le->colorText = colorText;
    le->hp = hp;
    le->mp = mp;
    le->lvl = lvl;
    le->xp = 0;
    le->xpNeeded = 200;
    initInventory(&le->inventory);
}

void addHP(LivingEntity* le, int hp) {
    le->hp += hp;
}

void addMP(LivingEntity* le, int mp) {
    le->mp += mp;
}

void addXP(LivingEntity* le, int xp) {
    le->xp += xp;
    while (le->xp >= le->xpNeeded) {
        int xpResidual = le->xp - le->xpNeeded;
        le->xp = xpResidual;
        le->lvl += 1;
        le->xpNeeded = (int)(le->xpNeeded * (le->lvl + 0.2));
    }
}

void setHP(LivingEntity* le, int hp) {
    le->hp = hp;
}

void setMP(LivingEntity* le, int mp) {
    le->mp = mp;
}

void changeName(LivingEntity* le, const char* name) {
    free(le->name);
    le->name = strdup(name);
}

int statusLife(const LivingEntity* le) {
    return le->hp > 0;
}

void addItem(LivingEntity* le, const char* itemName, int itemAmount) {
    setItem(&le->inventory, itemName, itemAmount);
}

void addItemWithText(LivingEntity* le, const char* text, const char* itemName, int itemAmount) {
    addItem(&le->inventory, itemName, itemAmount);
    printf("%s< %s >%s\n", getColorCode(YELLOW), text, getColorCode(RESET));
}

void printInventory(const LivingEntity* le) {
    printf("%s_ ��������� _ _ _ _ _ _ _%s\n", getColorCode(le->colorText), getColorCode(RESET));
    const InventoryItem* items = getInventory(&le->inventory);
    for (int i = 0; i < getItemsAmount(&le->inventory); i++) {
        printf("| -> �����: %d, ����: %s, ����������: %d\n", i, items[i].itemName, items[i].itemAmount);
    }
    printf("%s", getColorCode(RESET));
}

void getStatistics(const LivingEntity* le) {
    printf("%s_ ���������� _ _ _ _ _ _ _%s\n", getColorCode(le->colorText), getColorCode(RESET));
    printf("| -> ���: %s\n", le->name);
    printf("| -> XP: %d/%d\n", le->xp, le->xpNeeded);
    printf("| -> �������: %d\n", le->lvl);
    printf("| -> HP: %d\n", le->hp);
    printf("| -> MP: %d\n", le->mp);
    printf("%s", getColorCode(RESET));
}

void attack(LivingEntity* attacker, LivingEntity* defender, int damage) {
    addHP(defender, -damage); // ��������� �������� ���������
}

// ��������� � ������� ��� �����
typedef struct {
    char* name;
    Color colorText;
    int hp;
    int mp;
    int lvl;
} Enemy;

void initEnemy(Enemy* enemy, const char* name, Color colorText, int hp, int mp, int lvl) {
    enemy->name = strdup(name);
    enemy->colorText = colorText;
    enemy->hp = hp;
    enemy->mp = mp;
    enemy->lvl = lvl;
}

void attackEnemy(Enemy* enemy, LivingEntity* player, int damage) {
    addHP(player, -damage);
    printf("���� �������� ��� � ����� %d �����!\n", damage);
}

// ��������� � ������� ��� ������
typedef struct {
    char* name;
    Color colorText;
    int hp;
    int mp;
    int lvl;
} Player;

void initPlayer(Player* player, const char* name, Color colorText, int hp, int mp, int lvl) {
    player->name = strdup(name);
    player->colorText = colorText;
    player->hp = hp;
    player->mp = mp;
    player->lvl = lvl;
}

void attackPlayer(Player* player, Enemy* enemy, int damage) {
    addHP(enemy, damage);
    printf("�� ��������� ����� � ������� %d �����!\n", damage);
}

int getPlayerHP(const Player* player) {
    return player->hp;
}

// ������� ��� ����� � ������
void printText(const char* text) {
    printf("%s", text);
}

void printTextRepeated(const char* text, int amount, const char* endText) {
    for (int i = 0; i < amount; i++) {
        printf("%s", text);
    }
    printf("%s", endText);
}

#define MAX_INPUT_SIZE 256

char* getInput(const char* prompt) {
    static char buffer[MAX_INPUT_SIZE]; // ����������� ����� ��� �����
    printf("%s", prompt);

    if (fgets(buffer, sizeof(buffer), stdin) != NULL) {
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len-1] == '\n') {
            buffer[len-1] = '\0'; // �������� ������� ����� ������
        }
        return buffer;
    }

    return NULL;
}

void printlnText(Color color, const char* text) {
    printf("%s%s%s\n", getColorCode(color), text, getColorCode(RESET));
}

// ������� �������
int main() {
    setlocale(LC_ALL, "");

    LivingEntity enemy, player;
    initLivingEntity(&enemy, "�������", WHITE, 50, 20, 1);
    initLivingEntity(&player, "�����", PURPLE, 100, 50, 1);

    // ������� �������������� ���������
    printlnText(PURPLE, "����� ���������� � �����������!");

    // �������� ��� �������� �����
    char* input = getInput("������� ��� ������ �����: ");
    changeName(&player, input);
    printlnText(PURPLE, "������, ");
    printlnText(PURPLE, player.name);

    // ������ ����
    printlnText(PURPLE, "�� ���������� � ������ ����������. ���� ���� - ����� �����.");
    printlnText(PURPLE, "������� ��� ������������� �������.");

    // �������� ����� ������
    input = getInput("1. ����� ������. 2. ����� �������. > ");
    if (strcmp(input, "1") == 0) {
        printlnText(PURPLE, "�� ������������ ������ � ����� �� �������.");
        printlnText(PURPLE, "����� �� ������ ����� � ����� �������.");

        // �������� ����� ������
        input = getInput("1. ������� �����. 2. ��������� �������. > ");
        if (strcmp(input, "1") == 0) {
            printlnText(PURPLE, "�� ���������� ����� � �������� ������ � �����������.");
            addItem(&player, "���������", 1);
        } else if (strcmp(input, "2") == 0) {
            printlnText(PURPLE, "�� ���������������� � ������������� �������.");
        }
    } else if (strcmp(input, "2") == 0) {
        printlnText(PURPLE, "�� ������������ ������� � ����������� ����.");
        printlnText(PURPLE, "�������� �� ��������� � ������� � ������� ����� ��������.");
        addHP(&player, -20);
    }

    // ������� ���������� ������
    getStatistics(&player);

    // ���������� ����
    printlnText(PURPLE, "�� �������� ����� �����������. ������� �� ����!");

    // ������������ ���������� ������
    free(player.name);
    free(enemy.name);

    return 0;
}
