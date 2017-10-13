import java.util.ArrayList;
import java.util.Iterator;

public abstract class NumberGenerator {
	private ArrayList<Observer> observerCollection = new ArrayList<Observer>();

	public void registerObserver(Observer observer) {
		observerCollection.add(observer);
	}

	public void unregisterObserver(Observer observer) {
		observerCollection.remove(observer);
	}

	public void notifyObservers() {
		Iterator<Observer> it = observerCollection.iterator();
		while (it.hasNext()) {
			Observer o = it.next();
			o.update(this);
		}
	}

	public abstract int getNumber();

	public abstract void excute();
}
