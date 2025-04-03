public class Book extends Item implements PriceableWithVAT6 {
    private String author;
    private double price;
    private boolean bound;

    public Book(String name, String author, double price, boolean bound)
        super(name);
        this.author =author;
        this.price =price;
        this.bound =bound;
}

    @Override
    public String getType() {
        return "Book";
    }
    public String getAuthor() {
        return author;
    }
    public double getPrice() {
        double finalPrice = double ?price * 1.3 :price; //Indbunda böcker 30% dyrare
        return finalPrice * 1.06; //Lägg till 6% moms
    }
    public boolean isBound() {
        return bound;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setBound(boolean bound) {
        this.bound = bound;
    }
}
