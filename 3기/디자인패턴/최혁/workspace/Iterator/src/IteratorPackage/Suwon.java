package IteratorPackage;

import java.util.ArrayList;
import java.util.List;

public class Suwon implements Aggregate {
	List<String> listStr;
	public Suwon() {
		listStr = new ArrayList<String>();
		listStr.add("광교산");
		listStr.add("수원성");
		listStr.add("중평떡");
		listStr.add("수원갈비");
	}
	@Override
	public IteratorFunc iterator() {
		return new SuwonIterator(this);
	}

}
