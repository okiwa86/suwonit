package TemplteMethod_Ex2;

public class Duck implements Comparable {
	
	String name;
	int weight;
	
	public Duck(String name, int weight)
	{
		this.name = name;
		this.weight = weight;
	}
	
	public String ToString()
	{
		return (name + ", 체중 : " + weight);
	}

	public int compareTo(Object obj) {
		
		Duck otherDuck = (Duck)obj;
		
		if(this.weight < otherDuck.weight)
			return -1;
		else if(this.weight == otherDuck.weight)
			return 0;
		else 
			return 1;
	}
}
