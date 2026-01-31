package cafeboard.server;

import cafeboard.entities.BoardItem;
import cafeboard.entities.Item;
import cafeboard.entities.MenuItem;
import cafeboard.entities.SpecialItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuService {

    private BoardItem menu;
    private List<Item> items = new ArrayList<>();

    public MenuService(BoardItem menu) {
        this.menu = menu;
    }

    public void handleCommand(String line) {
        if (line.startsWith("add ")) {
            handleAdd(line);
        } else if (line.startsWith("delete ")) {
            handleDelete(line);
        } else {
            System.out.println("Wrong input.");
        }
    }

    public void handleAdd(String line) {
        double price;
        String[] parts = line.split(" ");
        if (parts.length < 4) {
            System.out.println("Wrong format.");
        }
        try {
            price = Double.parseDouble(parts[parts.length - 2 ]);
            int cutOff = line.lastIndexOf(" " + parts[parts.length - 2]);
            String name = line.substring(4, cutOff);
            if (Objects.equals(parts[parts.length - 1], "menu")) {
                MenuItem newItem = new MenuItem(name, price);
                menu.getMenuItems().add(newItem);

            } else if (Objects.equals(parts[parts.length - 1], "special")) {
                SpecialItem newSpecial = new SpecialItem(name, price);
                menu.getMenuItems().add(newSpecial);
            } else {
                System.out.println("Please correctly enter item as either menu OR special");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid value for price!");
        }
        System.out.println("SERVER MENU SIZE: "+ menu.getMenuItems().size());
    }

    public void handleDelete(String line) {
        boolean found = false;
        items = menu.getMenuItems();
        String product = line.substring(7);
        for (Item item: items) {
            if (product.equals(item.getName())) {
                items.remove(item);
                System.out.println("Product deleted: " + product);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product does not exist in menu.");
        }
        System.out.println("SERVER MENU SIZE: "+ menu.getMenuItems().size());
    }
 }
