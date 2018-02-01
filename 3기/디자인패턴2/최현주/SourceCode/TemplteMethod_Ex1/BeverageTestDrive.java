package TemplteMethod_Ex1;

public class BeverageTestDrive {
	public static void main(String[] args)
	{
		TeaWithHook teaHook = new TeaWithHook();
		CoffeeWithHook coffeeHook = new CoffeeWithHook();
		
		System.out.println("\n차를 준비중...");
		teaHook.PrepareRecipe();

		System.out.println("\n커피를 준비중...");
		coffeeHook.PrepareRecipe();
	}
}
