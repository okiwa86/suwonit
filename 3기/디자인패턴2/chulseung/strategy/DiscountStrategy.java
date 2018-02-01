package dp.strategy;

public interface DiscountStrategy {
	int getDiscountPrice(Item item);
	int getDiscountPrice(int totalPrice);
}
