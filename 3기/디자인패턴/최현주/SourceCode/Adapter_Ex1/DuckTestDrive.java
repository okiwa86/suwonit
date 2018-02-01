package Adapter_Ex1;

public class DuckTestDrive {
	public static void main(String[] args)
	{
		MallardDuck duck = new MallardDuck();
		
		ITurkey duckAdapter = new DuckAdapter(duck);
		
		WildTurkey turkey = new WildTurkey();
		IDuck turkeyAdapter = new TurkeyAdapter(turkey); 
		
		System.out.println("The turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("\nThe duckAdapter says...");
		testTurkey(duckAdapter);
		
		System.out.println("\nThe duck says...");
		testDuck(duck);
		
		System.out.println("\nThe turkeyAdapter says...");
		testDuck(turkeyAdapter);
	}
	
	static void testTurkey(ITurkey turkey)
	{
		turkey.gobble();
		turkey.fly();
	}
	
	static void testDuck(IDuck duck)
	{
		duck.quack();
		duck.fly();
	}
}
