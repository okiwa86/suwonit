package dp.decorator;

public abstract class Topping implements Cookie{

	protected Cookie cookie;

	public Topping(Cookie cookie) {
		this.cookie = cookie;
	}

	@Override
	public abstract String getName();
}
