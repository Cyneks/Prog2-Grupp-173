public abstract class Item implements Priceable {
    private final String name;

    private Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
