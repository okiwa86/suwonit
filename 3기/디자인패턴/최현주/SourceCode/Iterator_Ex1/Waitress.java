package Iterator_Ex1;

public class Waitress {
	IMenu pancakeHouseMenu;
    IMenu dinerMenu;
    IMenu cafeMenu;

    public Waitress(IMenu pancakeHouseMenu, IMenu dinerMenu)
    {
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
//        this.cafeMenu = cafeMenu;
	}

	public void printMenu()
	{
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerIterator = dinerMenu.createIterator();
//        Iterator cafeIterator = cafeMenu.createIterator();

        System.out.println("메뉴\n----아침 메뉴\n");
		printMenu(pancakeIterator);
		System.out.println("\n점심 메뉴");
		printMenu(dinerIterator);
//		System.out.println("\n저녁 메뉴");
//        printMenu(cafeIterator);
    }

	private void printMenu(Iterator iterator)
	{
		while(iterator.hasNext())
		{
			MenuItem menuItem = (MenuItem)iterator.next();

			System.out.println(menuItem.GetName() + ", " + menuItem.GetPrice() + "--" + menuItem.GetDescription());
		}
	}
}
