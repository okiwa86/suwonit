package IteratorPackage;

public class SuwonIterator implements IteratorFunc {
	Suwon suwon;
	int index;
	
	public SuwonIterator(Suwon suwon) {
		this.suwon = suwon;
		this.index = 0;
	}
	
	@Override
	public Object next() {
		String x = suwon.listStr.get(index);
		index ++;
		return x;
	}

	@Override
	public boolean hasNext() {
		if (index < suwon.listStr.size()) {
			return true;
		}else {
			return false;
		}
	}

}
