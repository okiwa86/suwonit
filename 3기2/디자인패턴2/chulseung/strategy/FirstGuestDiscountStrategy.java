package dp.strategy;

public class FirstGuestDiscountStrategy implements DiscountStrategy {

	@Override
	public int getDiscountPrice(Item item) {
	
		return (int) (item.getPrice() * 0.9);
	}

	@Override
	public int getDiscountPrice(int totalPrice) {
		// TODO Auto-generated method stub
		return 0;
	}

}
