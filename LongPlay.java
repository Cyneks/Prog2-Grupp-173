import java.time.Year;

public class LongPlay extends Recording{

    public LongPlay(String name, String artist, int year, int condition, double price){

        super(name, artist, year, condition, price);

    }

    public String getType(){

        return "LP";

    }

    public double getPrice(){

        return this.getOriginalPrice() * this.getCondition() + (Year.now().getValue() - this.getYear()) * 5;

    }

}