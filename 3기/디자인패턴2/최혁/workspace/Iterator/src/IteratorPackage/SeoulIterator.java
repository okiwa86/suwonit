package IteratorPackage;

public class SeoulIterator implements IteratorFunc {
	Seoul seoul;
	int index;
	
	public SeoulIterator(Seoul seoul) {
		this.seoul = seoul;
		this.index = 0;
	}
	
	@Override
	public Object next() {
		String x = seoul.map.get(index);
		index ++;
		return x;
	}

	@Override
	public boolean hasNext() {
		if (index < seoul.map.size()) {
			return true;
		}else {
			return false;
		}
	}

}
