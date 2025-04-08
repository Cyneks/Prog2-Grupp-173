public class Book extends Item implements PriceableWithVAT6 {
    //Ändrade instansvariablerna till final (se klassdiagrammet, små gråa symboler ovanför "f" symbolen)
    private final String author;
    private final double price;
    private final boolean bound;

    public Book(String name, String author, double price, boolean bound) {
        super(name);
        this.author = author;
        this.price = price;
        this.bound = bound;
    }

    private String getType() {
        return "Book";
    }

    private String getAuthor() {
        return author;
    }

    public double getPrice() {
        double finalPrice = isBound() ? price * 1.3 : price; //Indbunda böcker 30% dyrare
        return finalPrice; //Tog bort *1.06 då metoden getPriceWithVAT() sköter det istället - Mattias
    }

    private boolean isBound() {
        return bound;
    }

    //Tog bort set metoderna då det inte ska finnas några enligt klassdiagrammet - Mattias

    public String toString() {
        return this.getType()+ ": name=" + this.getName() + ", author=" + this.getAuthor() + ", bound=" + this.isBound() + ", price=" + this.getPrice() + ", price+vat=" + this.getPriceWithVAT();
    }
}
