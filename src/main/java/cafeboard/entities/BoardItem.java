package cafeboard.entities;

import java.util.List;

public class BoardItem {

    private String cafeName;
    private List<Item> menuItems;

    public BoardItem(String cafeName, List<Item> menuItems) {
        this.cafeName = cafeName;
        this.menuItems = menuItems;
    }

    public List<Item> getMenuItems() {
        return menuItems;
    }
}
