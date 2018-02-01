package dp.strategy;

import java.util.List;

public class TestStrategyCal {

	private static final List<Item> Item = null;
	private DiscountStrategy strategy;
	
	public void onFirstGuestButtonClick() {
		strategy  =  new FirstGuestDiscountStrategy();
	}
	
	public void onLastGuestButtonClick() {
		strategy  =  new LastGuestDiscountStrategy();
	}
	
	public void onCalculationButtonClick() {
		Calculator cal = new Calculator(strategy);
		
		List<Item> items = Item;
		int price = cal.calculator(items);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
}
