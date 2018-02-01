package framework;

public interface Product extends Cloneable {
	void use(String s);
	Product createClone();
}
