import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private static long counter;
    private final long orderNumber;
    
    private final List<Item> items;

    public Order(Item... items){
        this.items = new ArrayList<>();
        for (Item item : items){
            this.items.addAll(Arrays.asList(item));
        }
        
        counter++;
        orderNumber = counter;
    }

    public String getReceipt(){
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt for order #").append(orderNumber + "\n").append("-----------\n");

        for (Item item : items){
            if (item instanceof Book) receipt.append(getBookInfo((Book)item) + "\n");
            else if (item instanceof CompactDisc) receipt.append(getCDInfo((CompactDisc)item) + "\n");
            else receipt.append(getLPInfo((LongPlay)item));
        }

        receipt.append("\nTotal excl . VAT : " + getTotalValue() + "\nTotal incl . VAT : " + getTotalValuePlusVAT());
        return receipt.toString();
    }

    public double getTotalValuePlusVAT(){
        double totalAmountWithVAT = 0;

        for (Item item : items){
            totalAmountWithVAT += item.getPrice() + item.getPrice()*item.getVAT();
        }

        return totalAmountWithVAT;
    }

    public double getTotalValue(){
        double totalAmount = 0;

        for (Item item : items){
            totalAmount += item.getPrice();
        }

        return totalAmount;
    }

    private String getBookInfo(Book item){
        return item.toString();
    }

    private String getLPInfo(LongPlay item){
        return item.toString();
    }

    private String getCDInfo(CompactDisc item){
        return item.toString();
    }

    private double getPriceVAT(Item item){
        return item.getPrice() + item.getPrice() * item.getVAT();
    }

}
