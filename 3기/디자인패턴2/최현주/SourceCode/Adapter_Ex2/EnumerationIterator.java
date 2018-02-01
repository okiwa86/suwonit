package Adapter_Ex2;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator implements Iterator {

	Enumeration enum;
	
	public EnumerationIterator(Enumeration enum)
	{
		this.enum = enum;
	}
	
	public boolean hasNext() {
		return enum.hasMoreElements();
	}

	public Object next() {
		return enum.nextElement();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
