
public class Singleton {
	// why private? can't create this class outside
	private static Singleton singleton = new Singleton();

	private Singleton() {
		// check created
		System.out.println("create singleton class");
	}

	public static Singleton getInstance() {
		return singleton;
	}
}
