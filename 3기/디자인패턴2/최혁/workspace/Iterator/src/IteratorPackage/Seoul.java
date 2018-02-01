package IteratorPackage;

import java.util.HashMap;
import java.util.Map;

public class Seoul implements Aggregate {
	Map<Integer, String> map;
	
	public Seoul() {
		map = new HashMap<Integer,String>();
		map.put(0, "경복궁");
		map.put(1, "한강");
		map.put(2, "뭐가있지?");
	}
	
	@Override
	public IteratorFunc iterator() {
		return new SeoulIterator(this);
	}

}
