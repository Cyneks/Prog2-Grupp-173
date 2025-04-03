import java.time.Year;

public class LongPlay extends Recording{
    public LongPlay(String name, String artist, int year, int condition, double price){
        super(name, artist, year, condition, price);
    }

    public String getType(){
        return "LP";
    }

    public double getPrice(){
        double calculatedPrice = this.getOriginalPrice() * ((double) this.getCondition() / 10) + (Year.now().getValue() - this.getYear()) * 5;

        if (calculatedPrice < 10) {
            return 10;
        }
        
        return this.getOriginalPrice() * ((double) this.getCondition() / 10) + (Year.now().getValue() - this.getYear()) * 5;
    }
}
