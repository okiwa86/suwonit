package Iterator_Ex1;

public class MenuTestDrive {
	public static void main(String[] args)
	{
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		DinerMenu dinerMenu = new DinerMenu();
//	    CafeMenu cafeMenu = new CafeMenu();
	
		Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
		waitress.printMenu();
	}
}
