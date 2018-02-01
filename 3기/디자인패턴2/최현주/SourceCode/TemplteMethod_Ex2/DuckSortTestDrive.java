package TemplteMethod_Ex2;

import java.util.Arrays;

public class DuckSortTestDrive {
	public static void main(String[] args)
	{
		Duck[] ducks = {
						new Duck("Duck8", 8),
						new Duck("Duck2-1", 2),
						new Duck("Duck7", 7),
						new Duck("Duck2-2", 2),
						new Duck("Duck10", 10),
						new Duck("Duck2-3", 2)
				
		};
		
		System.out.println("정렬 전:");
		display(ducks);
		
		Arrays.sort(ducks);

		System.out.println("정렬 후:");
		display(ducks);
	}
	
	public static void display(Duck[] ducks)
	{
		for(int i=0; i<ducks.length; i++)
		{
			System.out.println(ducks[i].ToString());
		}
	}

}
