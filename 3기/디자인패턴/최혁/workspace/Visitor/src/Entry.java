import java.util.Iterator;

public abstract class Entry implements Element {
	public abstract String getName();

	public abstract int getSize();

	public Entry add(Entry entry) throws FileTreatmentException {
		throw new FileTreatmentException();
	}

	public Iterator<?> iterator() throws FileTreatmentException {
		throw new FileTreatmentException();
	}

	// public void printList() {
	// 	printList("");
	// }

	// protected abstract void printList(String prefix);

	/**
	 * getName , getSize 는 추상클래스 이지만 하위클래스에서 이 두 메소드를 구현하여 toString에서
	 * 호출하고있습니다.(Template Pattern)
	 */
	public String toString() {
		return getName() + " (" + getSize() + ")";
	}
}
