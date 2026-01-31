package cafeboard.entities;

public class MenuItem extends Item {

    public MenuItem() {}

    public MenuItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "menu";
    }

    @Override
    public String toString() {
        return String.format("%-20s %5.2f € \n", this.name, this.price);
    }
}
