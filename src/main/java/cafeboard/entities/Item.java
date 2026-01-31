package cafeboard.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MenuItem.class, name = "menu"),
        @JsonSubTypes.Type(value = SpecialItem.class, name = "special")
})

public abstract class Item {

    protected String name;
    protected double price;
    private boolean favourite;

    public Item(){}

    public Item (String name, double price) {
        this.name = name;
        this.price = price;
        this.favourite = false;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void toggleFavourite() {
        this.favourite = !this.favourite;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return this.price;
    }

    public abstract String getType();
}
