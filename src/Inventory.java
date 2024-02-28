import java.util.HashMap;
public class Inventory {
    HashMap<String, Integer> inventory = new HashMap<>();

    public void setItem(String itemName, int itemAmount) {
        if (inventory.containsKey(itemName))
            inventory.put(itemName, getAmount(itemName) + itemAmount);
        else
            inventory.put(itemName, itemAmount);
    }

    public int getAmount(String itemName) {
        return inventory.getOrDefault(itemName, 0);
    }

    public int getItemsAmount() {
        return inventory.size();
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }
}
