package dp.decorator;

public class ChocolateTopping extends Topping{

	public ChocolateTopping(Cookie cookie) {
		super(cookie);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getName() {
		return "ÂÉ²¿¸À " + cookie.getName();
	}
}
