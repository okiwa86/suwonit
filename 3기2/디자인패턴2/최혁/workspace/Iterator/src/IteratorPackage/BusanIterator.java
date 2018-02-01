package IteratorPackage;

public class BusanIterator implements IteratorFunc {
	Busan busan;
	int index;
	
	public BusanIterator(Busan busan) {
		this.busan = busan;
		this.index = 0;
	}
	
	@Override
	public Object next() {
		String x = busan.arr[index];
		index ++;
		return x;
	}

	@Override
	public boolean hasNext() {
		if (index < busan.length) {
			return true;
		}else {
			return false;
		}
	}

}
