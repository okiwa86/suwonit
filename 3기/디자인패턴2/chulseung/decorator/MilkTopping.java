package dp.decorator;

public class MilkTopping extends Topping {

	public MilkTopping(Cookie cookie) {
		super(cookie);
	}

	@Override
	public String getName() {
		return "¿ìÀ¯¸À " + cookie.getName();
	}
}
