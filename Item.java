public abstract class Item implements Priceable {

    protected String name;

    public Item(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}


