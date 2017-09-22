package dp.strategy;

import java.util.List;

public class Calculator {

	private DiscountStrategy discountStrategy;

	public Calculator(DiscountStrategy discountStrategy) {
//		super();
		this.discountStrategy = discountStrategy;
	}
	
	public int calculator(List<Item> items) {
		int sum = 0;
		for (Item item :  items) {
			sum += discountStrategy.getDiscountPrice(item);
		}
		return sum;
	}
	
}
