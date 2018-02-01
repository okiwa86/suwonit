import java.util.ArrayList;
import java.util.Iterator;

public class Directory extends Entry {
	private String name;
	private ArrayList<Entry> dir = new ArrayList<Entry>(); // 안에 담길 Entry List

	public Directory(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		int size = 0;
		Iterator<Entry> it = dir.iterator();
		while (it.hasNext()) {
			Entry entry = it.next();
			size += entry.getSize();
		}
		return size;
	}

	public Entry add(Entry entry) {
		dir.add(entry);
		return this;
	}
	
	public Iterator<Entry> iterator() {
		return dir.iterator();
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

//	@Override
//	protected void printList(String prefix) {
//		System.out.println(prefix + "/" + this); // this == this.toString() { getName() + " (" + getSize() + ")"; }
//		Iterator<Entry> it = directory.iterator();
//		while (it.hasNext()) {
//			Entry entry = (Entry) it.next();
//			entry.printList(prefix + "/" + name);
//		}
//	}
	
	

}
