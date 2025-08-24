package restaurant;

import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<Item> menu;
    private boolean isOpen;

    public Restaurant(int id, String name, List<Item> menu, boolean isOpen) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.isOpen = isOpen;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void closeItem(int itemId) {
        for (Item item : menu) {
            if (item.getId() == itemId) {
                item.setAvailable(false);
                break;
            }
        }
    }

    public Item getItem(int itemId) {
        for (Item item : menu) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }
}
