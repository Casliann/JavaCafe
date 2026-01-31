package cafeboard.server;

import cafeboard.entities.BoardItem;
import cafeboard.entities.Item;
import cafeboard.entities.MenuItem;
import cafeboard.entities.SpecialItem;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {

    public BoardItem getData() {
        MenuItem catpuccino = new MenuItem("Catpuccino", 4.50);
        MenuItem kitTea = new MenuItem("Kit Tea", 3.10);
        SpecialItem meowcchiato = new SpecialItem("Meowcchiato", 4.60);

        List<Item> menulist = new ArrayList<>();
        menulist.add(catpuccino);
        menulist.add(kitTea);
        menulist.add(meowcchiato);

        return new BoardItem("Cat Café", menulist);
    }
}
