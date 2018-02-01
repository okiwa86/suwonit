package IteratorPackage;

public class Busan implements Aggregate {
	String[] arr;
	public int length;
	
	public Busan() {
		arr = new String[3];
		arr[0] = ("광안리 해수욕장");
		arr[1] = ("해운대 해수욕장");
		arr[2] = ("돼지국밥");
		
		this.length = arr.length;
	}
	
	@Override
	public IteratorFunc iterator() {
		return new BusanIterator(this);
	}
}
