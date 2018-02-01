
public class Main {
	public static void main(String[] args) {
		NumberGenerator generator = new RandomNumberGenerator();
		Observer observer1 = new DigitObserver();
		Observer observer2 = new HexObserver();
		generator.registerObserver(observer1);
		generator.registerObserver(observer2);
		generator.excute();
	}
}
