public interface PriceableWithVAT25 extends Priceable{
    default double getVAT() {
        return 0.25;
    }
}
