public abstract class Recording extends Item implements PriceableWithVAT25{
    private final String artist;
    private final int year;
    private final double price;

    private int condition;

    protected Recording(String name, String artist, int year, int condition, double price) {
        super(name);
        this.artist = artist;
        this.year = year;
        this.condition = condition;
        this.price = price;
    }

    public String getArtist() {
        return artist;
    }

    public abstract String getType();

    public int getCondition() {
        return condition;
    }

    public double getPrice() {
        double calculatedPrice = price*((double) condition/10);

        if (calculatedPrice < 10) {
            return 10;
        }

        return price * ((double) condition/10);
    }

    protected double getOriginalPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
    
    public String toString() {
        return this.getType() + ": name=" + this.getName() + ", artist=" + this.getArtist() + ", year=" + this.getYear() + ", condition=" + this.getCondition() + ", original price=" + this.getOriginalPrice() + ", price=" + this.getPrice() + ", price+vat=" + this.getPriceWithVAT();
    }
}
