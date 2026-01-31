package cafeboard.entities;

public class SpecialItem extends Item {

    public SpecialItem() {}

    public SpecialItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "special";
    }

    @Override
    public String toString() {
        return String.format("*%-20s %4.2f € \n", this.name, this.price);
    }
}
