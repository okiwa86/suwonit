
public class HexObserver implements Observer {
	@Override
	public void update(NumberGenerator generator) {
		System.out.println("HexObserver:" + Integer.toHexString(generator.getNumber()));
	}
}
